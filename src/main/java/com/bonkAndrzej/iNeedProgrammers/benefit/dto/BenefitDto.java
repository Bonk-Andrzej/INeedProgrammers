package com.bonkAndrzej.iNeedProgrammers.benefit.dto;

import com.bonkAndrzej.iNeedProgrammers.benefit.Benefit;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class BenefitDto {

    private Long id;
    private String uuid;
    private String name;
    private Integer version;

    public BenefitDto(Benefit benefit) {
        this.id = benefit.getId();
        this.uuid = benefit.getUuid().toString();
        this.name = benefit.getName();
        this.version = benefit.getVersion();
    }
}
