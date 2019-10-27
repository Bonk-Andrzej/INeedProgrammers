package com.bonkAndrzej.iNeedProgrammers.benefit.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class BenefitForm {

    @NotBlank private String name;
    @PositiveOrZero private Integer version;

}
