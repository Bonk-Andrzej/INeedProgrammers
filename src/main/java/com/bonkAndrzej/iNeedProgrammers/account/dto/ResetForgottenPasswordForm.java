package com.bonkAndrzej.iNeedProgrammers.account.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Form object for storing the user's key and password.
 */
@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class ResetForgottenPasswordForm {

    @NotBlank
    private String resetPasswordKey;
    @NotBlank @Size(min = 6, max = 100)
    private String newPassword;


}
