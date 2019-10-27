package com.bonkAndrzej.iNeedProgrammers.location.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class LocationForm {

    @NotBlank private String name;
    @PositiveOrZero private Integer version;

}
