package com.bonkAndrzej.iNeedProgrammers.user.role.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class RoleForm {

    @PositiveOrZero
    private Integer version;
    @NotBlank
    private String name;
    @NotBlank
    private String slug;

}
