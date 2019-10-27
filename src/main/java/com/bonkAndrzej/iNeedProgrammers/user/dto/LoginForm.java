package com.bonkAndrzej.iNeedProgrammers.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class LoginForm {

    @Email
    private String email;
    @NotBlank @Size(min = 4, max = 100)
    private String password;
    private boolean rememberMe;

}
