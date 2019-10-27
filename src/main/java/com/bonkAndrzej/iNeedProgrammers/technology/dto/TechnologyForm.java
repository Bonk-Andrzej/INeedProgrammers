package com.bonkAndrzej.iNeedProgrammers.technology.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class TechnologyForm {

    @NotBlank private String name;
    @PositiveOrZero private Integer version;
}
