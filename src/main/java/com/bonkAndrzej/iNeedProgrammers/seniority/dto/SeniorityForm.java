package com.bonkAndrzej.iNeedProgrammers.seniority.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class SeniorityForm {

    @NotBlank private String name;
    @PositiveOrZero private Integer version;
}
