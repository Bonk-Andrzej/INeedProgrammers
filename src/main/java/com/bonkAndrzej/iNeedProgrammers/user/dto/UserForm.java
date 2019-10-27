package com.bonkAndrzej.iNeedProgrammers.user.dto;

import com.bonkAndrzej.iNeedProgrammers.user.validator.RolesValidator;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class UserForm {

    @PositiveOrZero
    private Integer version;
    private String firstName;
    private String lastName;
    @Size(min = 5, max = 50)
    private String login;
    @Email
    private String email;
    @NotBlank @Size(min = 6, max = 100)
    private String password;

    @RolesValidator
    private String role;

}
