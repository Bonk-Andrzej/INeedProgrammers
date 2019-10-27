package com.bonkAndrzej.iNeedProgrammers.user.role.dto;


import com.bonkAndrzej.iNeedProgrammers.user.role.Role;
import lombok.*;

/**
 * A DTO representing a userRole.
 */
@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private Integer version;
    private String name;
    private String slug;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.version = role.getVersion();
        this.name = role.getName();
        this.slug = role.getSlug();
    }


}
