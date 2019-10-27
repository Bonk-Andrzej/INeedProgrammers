package com.bonkAndrzej.iNeedProgrammers.technology.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class TechnologyForm {

    @NotBlank
    private String name;
}
