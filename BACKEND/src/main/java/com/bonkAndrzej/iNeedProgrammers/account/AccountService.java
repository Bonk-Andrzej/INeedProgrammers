package com.bonkAndrzej.iNeedProgrammers.account;

import com.bonkAndrzej.iNeedProgrammers.account.dto.ChangePasswordForm;
import com.bonkAndrzej.iNeedProgrammers.account.dto.ResetForgottenPasswordForm;
import com.bonkAndrzej.iNeedProgrammers.account.exception.AccountException;
import com.bonkAndrzej.iNeedProgrammers.email.EmailService;
import com.bonkAndrzej.iNeedProgrammers.security.Auth;
import com.bonkAndrzej.iNeedProgrammers.security.AuthUserDetails;
import com.bonkAndrzej.iNeedProgrammers.security.jwtConfig.TokenProvider;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import com.bonkAndrzej.iNeedProgrammers.user.UserService;
import com.bonkAndrzej.iNeedProgrammers.user.dto.LoginForm;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserForm;
import com.bonkAndrzej.iNeedProgrammers.user.exception.UserException;
import com.bonkAndrzej.iNeedProgrammers.util.RandomStringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service @Transactional
@AllArgsConstructor
public class AccountService {

    private final Auth auth;
    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserDto registerUser(UserForm userForm) throws AccountException, UserException {
        checkUserForRegistration(userForm);

        User user = userService.assignFieldsToUser(new User(), userForm);
        user.setActivationKey(RandomStringUtil.generateActivationKey());
        user.setEnabled(false);
        User userAfterSave = userService.saveUser(user);
        emailService.sendActivationEmail(userAfterSave);
        return new UserDto(userAfterSave);
    }

    public UserDto activateAccount(String key) throws AccountException {
        log.debug("Activating user for activation key {}", key);
        User user = userService.findOneByActivationKey(key)
                .orElseThrow(() -> new AccountException("User not found for this activation key"));
        user.setEnabled(true);
        user.setActivationKey(null);
        log.debug("Activated user: {}", user);
        return new UserDto(user);
    }

    public JWToken login(LoginForm loginForm) throws AccountException {
        validateMailAndPassword(loginForm);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = loginForm.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        return new JWToken(jwt);
    }

    public void changeAuthUserPassword(ChangePasswordForm changePasswordForm)
            throws AccountException {

        AuthUserDetails authUserDetails = auth.getCurrentUserDetails()
                .orElseThrow(() -> new AccountException("User aren't authenticated"));

        User user = userService.findOneByUsername(authUserDetails.getUsername())
                .orElseThrow(() -> new AccountException("User not found"));

        if (!passwordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword()))
            throw new AccountException("Wrong password");

        String encryptedPassword = passwordEncoder.encode(changePasswordForm.getNewPassword());
        user.setPassword(encryptedPassword);
        userService.saveUser(user);
    }

    public void resetPasswordInit(String email) throws AccountException {
        User emailReceiver = userService.findOneByUsername(email)
                .map(user -> {
                    user.setResetPasswordKey(UUID.randomUUID().toString());
                    user.setResetPasswordDate(Instant.now());
                    return user;
                }).orElseThrow(() -> new AccountException("Wrong email or user not exist"));

        emailService.sendPasswordResetEMail(emailReceiver);
    }


    public UserDto resetPasswordFinish(ResetForgottenPasswordForm resetPasswordForm) throws AccountException {
        return userService.findOneByResetPasswordKey(resetPasswordForm.getResetPasswordKey())
                .filter(user -> user.getResetPasswordDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(resetPasswordForm.getNewPassword()));
                    user.setResetPasswordKey(null);
                    user.setResetPasswordDate(null);
                    return new UserDto(user);
                }).orElseThrow(() -> new AccountException("User not found. Check please resetPasswordKey"));
    }

    private void validateMailAndPassword(LoginForm loginForm) throws AccountException {

        userService.getUserEmail(loginForm.getEmail()).orElseThrow(() -> new AccountException("Wrong email"));
        String loginPassword = loginForm.getPassword();
        Set<String> encryptedPasswords = userService.getAllEncodedPasswords();
        if (encryptedPasswords.stream().noneMatch(currentPassword ->
                passwordEncoder.matches(loginPassword, currentPassword)))
            throw new AccountException("Wrong password");


    }

    //    If user exist and is enabled - some user already use this email
    //    Otherwise exist and isn't enabled - remove from DB and continue registration process
    private void checkUserForRegistration(UserForm userForm) throws AccountException {
        if (userService.findOneByUsername(userForm.getEmail()).isPresent() &&
                userService.findOneByUsername(userForm.getEmail()).stream().allMatch(User::isEnabled))
            throw new AccountException("Email already in use");

        userService.findOneByUsername(userForm.getEmail()).ifPresent(user -> {
            if (!user.isEnabled())
                userService.removeUser(user);
        });
    }

}
