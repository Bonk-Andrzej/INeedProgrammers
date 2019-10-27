package com.bonkAndrzej.iNeedProgrammers.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class CategoryForm {

     @NotBlank private String name;
     @PositiveOrZero private Integer version;
}
