package com.bonkAndrzej.iNeedProgrammers.user.dto;

import com.bonkAndrzej.iNeedProgrammers.user.User;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class UserDto {

    private Long id;
    private Integer version;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
    private String resetPasswordKey;
    private String resetPasswordDate;
    private boolean isEnabled;

    private Long roleId;

    public UserDto(User user) {
        this.id = user.getId();
        this.version = user.getVersion();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.resetPasswordKey = user.getResetPasswordKey();

        this.resetPasswordDate = user.getResetPasswordDate() != null
                ? user.getResetPasswordDate().toString()
                : null;

        this.isEnabled = user.isEnabled();
        this.roleId = user.getRole().getId();
    }

}
