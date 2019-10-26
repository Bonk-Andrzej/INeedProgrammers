package com.bonkAndrzej.iNeedProgrammers.location.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class LocationForm {

    @NotBlank private String name;
}
