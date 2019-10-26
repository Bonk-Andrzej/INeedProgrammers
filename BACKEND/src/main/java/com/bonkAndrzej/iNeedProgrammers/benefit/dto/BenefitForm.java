package com.bonkAndrzej.iNeedProgrammers.benefit.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class BenefitForm {

    @NotBlank
    private String name;

}
