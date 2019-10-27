package com.bonkAndrzej.iNeedProgrammers.benefit.dto;

import com.bonkAndrzej.iNeedProgrammers.benefit.Benefit;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class BenefitDto {

    private String name;

    public BenefitDto(Benefit benefit) {
        this.name = benefit.getName();
    }
}
