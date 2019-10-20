package com.bonkAndrzej.iNeedProgrammers.account;

import com.bonkAndrzej.iNeedProgrammers.account.dto.ChangePasswordForm;
import com.bonkAndrzej.iNeedProgrammers.account.dto.ResetForgottenPasswordForm;
import com.bonkAndrzej.iNeedProgrammers.email.EmailService;
import com.bonkAndrzej.iNeedProgrammers.security.Auth;
import com.bonkAndrzej.iNeedProgrammers.security.CurrentUserDetails;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import com.bonkAndrzej.iNeedProgrammers.user.UserService;

import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.util.error.CustomValidationException;
import com.bonkAndrzej.iNeedProgrammers.util.error.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final UserService userService;
    private final EmailService emailService;
    private final Auth auth;
    private final PasswordEncoder passwordEncoder;


    public void changeAuthUserPassword(ChangePasswordForm changePasswordForm) throws ResourceNotFoundException, CustomValidationException {
        CurrentUserDetails currentUserDetails = auth.getCurrentUserDetails()
            .orElseThrow(() -> new CustomValidationException("User aren't authenticated"));

        User user = userService.getUserByUsername(currentUserDetails.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword()))
            throw new CustomValidationException("Wrong password");

        String encryptedPassword = passwordEncoder.encode(changePasswordForm.getNewPassword());
        user.setPassword(encryptedPassword);
        userService.saveUser(user);
    }

    public void resetPasswordInit(String email) throws CustomValidationException {
        User emailReceiver = userService.getUserByUsername(email)
            .map(user -> {
                user.setResetPasswordKey(UUID.randomUUID().toString());
                user.setResetPasswordDate(Instant.now());
                return user;
            }).orElseThrow(() -> new CustomValidationException("Wrong email or user not exist"));

        emailService.sendPasswordResetEMail(emailReceiver);
    }


    public UserDto resetPasswordFinish(ResetForgottenPasswordForm resetPasswordForm) throws ResourceNotFoundException {
        return userService.findOneByResetPasswordKey(resetPasswordForm.getResetPasswordKey())
            .filter(user -> user.getResetPasswordDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(resetPasswordForm.getNewPassword()));
                user.setResetPasswordKey(null);
                user.setResetPasswordDate(null);
                return new UserDto(user);
            }).orElseThrow(() -> new ResourceNotFoundException("User not found. Check please resetPasswordKey"));
    }
}
