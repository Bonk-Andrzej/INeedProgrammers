package com.bonkAndrzej.iNeedProgrammers.technology.dto;

import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class TechnologyDto {

    private String name;

    public TechnologyDto(Technology technology) {
        this.name = technology.getName();
    }
}
