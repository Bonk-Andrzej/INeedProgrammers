package com.bonkAndrzej.iNeedProgrammers.account;

import com.bonkAndrzej.iNeedProgrammers.account.dto.ChangePasswordForm;
import com.bonkAndrzej.iNeedProgrammers.account.dto.ResetForgottenPasswordForm;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.util.error.CustomValidationException;
import com.bonkAndrzej.iNeedProgrammers.util.error.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * REST controller for managing the current user's account.
 */
@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @PostMapping("/account/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm)
        throws ResourceNotFoundException, CustomValidationException {

        accountService.changeAuthUserPassword(changePasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/account/reset-password/init")
    public void resetPasswordInit(@Email @RequestBody String email) throws CustomValidationException {
        accountService.resetPasswordInit(email);
    }

    @PostMapping("/account/reset-password/finish")
    public ResponseEntity<UserDto> resetPasswordFinish(@Valid @RequestBody ResetForgottenPasswordForm resetPasswordForm)
        throws ResourceNotFoundException {

        UserDto userDto = accountService.resetPasswordFinish(resetPasswordForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}
