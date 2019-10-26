package com.bonkAndrzej.iNeedProgrammers.category.dto;

import com.bonkAndrzej.iNeedProgrammers.category.Category;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class CategoryDto {
    private String name;

    public CategoryDto(Category category) {
        this.name = category.getName();
    }
}
