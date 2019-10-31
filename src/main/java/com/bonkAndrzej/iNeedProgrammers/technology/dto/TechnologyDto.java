package com.bonkAndrzej.iNeedProgrammers.technology.dto;

import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class TechnologyDto {
    private Long id;
    private String uuid;
    private String name;
    private Integer version;

    public TechnologyDto(Technology technology) {
        this.id = technology.getId();
        this.uuid = technology.getUuid().toString();
        this.name = technology.getName();
        this.version = technology.getVersion();
    }

}
