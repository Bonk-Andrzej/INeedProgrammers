package com.bonkAndrzej.iNeedProgrammers.category.dto;
import com.bonkAndrzej.iNeedProgrammers.category.Category;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String uuid;
    private String name;
    private Integer version;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.uuid = category.getUuid().toString();
        this.name = category.getName();
        this.version = category.getVersion();
    }
}
