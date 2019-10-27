package com.bonkAndrzej.iNeedProgrammers.seniority.dto;

import com.bonkAndrzej.iNeedProgrammers.seniority.Seniority;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class SeniorityDto {

    private String name;
    private Integer version;


    public SeniorityDto(Seniority seniority) {
        this.name = seniority.getName();
        this.version = seniority.getVersion();
    }
}
