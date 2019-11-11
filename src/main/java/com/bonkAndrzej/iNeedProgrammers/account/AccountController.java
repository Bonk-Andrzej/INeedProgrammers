package com.bonkAndrzej.iNeedProgrammers.account;

import com.bonkAndrzej.iNeedProgrammers.account.dto.ChangePasswordForm;
import com.bonkAndrzej.iNeedProgrammers.account.dto.ResetForgottenPasswordForm;
import com.bonkAndrzej.iNeedProgrammers.account.exception.AccountException;
import com.bonkAndrzej.iNeedProgrammers.security.jwtConfig.JWTFilter;
import com.bonkAndrzej.iNeedProgrammers.user.dto.LoginForm;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserForm;
import com.bonkAndrzej.iNeedProgrammers.user.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * REST controller for managing the current user's account.
 */
@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final AccountService accountService;


    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws AccountException {@code 400 (Bad Request)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public UserDto getAccount() throws AccountException {
        return accountService.getUserWithAuthorities()
                .map(UserDto::new)
                .orElseThrow(() -> new AccountException("User could not be found"));
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param userForm the managed userForm.
     * @return new ResponseEntity(userDto, HttpStatus.CREATED)
     * @throws AccountException {@code 400 (Bad Request)} if user can't be registered.
     * @throws UserException    {@code 400 (Bad Request)} if is error with assign properties to user.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerAccount(@Valid @RequestBody UserForm userForm)
            throws AccountException, UserException {

        UserDto userDto = accountService.registerUser(userForm);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    /**
     * {@code GET  /activate} : activate the registered user.
     *
     * @param key the activation key.
     * @return new ResponseEntity(userDto, HttpStatus.CREATED)
     * @throws AccountException {@code 404 (Not Found)} if the user can't be activated with given key.
     */
    @GetMapping("/activate")
    public ResponseEntity<UserDto> activateAccount(@NotBlank @RequestParam(value = "key") String key)
            throws AccountException {
        UserDto userDto = accountService.activateAccount(key);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the String login if the user is authenticated or null if it is not.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * {@code POST  /authenticate/login-user} : login user.
     *
     * @param loginForm the user to login.
     * @return new ResponseEntity<>(jwtToken, httpHeaders, HttpStatus.OK).
     * @throws AccountException {@code 404 (Bad Request)} } if the user can't login.
     */
    @PostMapping("/authenticate/login-user")
    public ResponseEntity<JWToken> loginUser(@Valid @RequestBody LoginForm loginForm)
            throws AccountException {

        JWToken JWToken = accountService.login(loginForm);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + JWToken.getIdToken());
        return new ResponseEntity<>(JWToken, httpHeaders, HttpStatus.OK);
    }

    /**
     * {@code POST  /account/change-password} :  changes the current user's password.
     *
     * @param changePasswordForm current and new password.
     * @return new ResponseEntity<>(HttpStatus.OK).
     * @throws AccountException {@code 404 (Bad Request)} } if the user can't change password.
     */
    @PostMapping("/account/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm)
            throws AccountException {

        accountService.changeAuthUserPassword(changePasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * {@code POST /account/reset-password/init} : initiate password reset.
     *
     * @param email email address where the reset email password will be sent.
     * @return void
     * @throws AccountException {@code 404 (Bad Request)} } if user with given email not exist.
     */
    @PostMapping("/account/reset-password/init")
    public void resetPasswordInit(@Email @RequestBody String email) throws AccountException {
        accountService.resetPasswordInit(email);
    }

    /**
     * {@code POST /account/reset-password/finish} : finishing password reset.
     *
     * @param resetForgottenPasswordForm reset password key and new password.
     * @return return new ResponseEntity<>(userDto, HttpStatus.OK).
     * @throws AccountException {@code 404 (Not Found)} } if user not found with given reset password key.
     */
    @PostMapping("/account/reset-password/finish")
    public ResponseEntity<UserDto> resetPasswordFinish(@Valid @RequestBody ResetForgottenPasswordForm resetForgottenPasswordForm)
            throws AccountException {

        UserDto userDto = accountService.resetPasswordFinish(resetForgottenPasswordForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
