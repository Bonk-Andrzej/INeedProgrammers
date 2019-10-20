package com.bonkAndrzej.iNeedProgrammers.account.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Form object representing a password change required data - current and new password.
 */
@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor

public class ChangePasswordForm {

    @NotBlank @Size(min = 6, max = 100)
    private String currentPassword;

    @NotBlank @Size(min = 6, max = 100)
    private String newPassword;
}
