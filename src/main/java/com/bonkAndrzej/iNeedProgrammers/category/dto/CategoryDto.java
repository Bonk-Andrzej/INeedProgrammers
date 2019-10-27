package com.bonkAndrzej.iNeedProgrammers.category.dto;

import com.bonkAndrzej.iNeedProgrammers.category.Category;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class CategoryDto {

    private String name;
    private Integer version;

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.version = category.getVersion();
    }
}
