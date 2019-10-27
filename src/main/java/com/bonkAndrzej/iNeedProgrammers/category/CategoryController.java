package com.bonkAndrzej.iNeedProgrammers.category;

import com.bonkAndrzej.iNeedProgrammers.category.dto.CategoryDto;
import com.bonkAndrzej.iNeedProgrammers.category.dto.CategoryForm;
import com.bonkAndrzej.iNeedProgrammers.category.exception.CategoryException;
import com.bonkAndrzej.iNeedProgrammers.user.role.roleAnnotation.Admin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Admin
    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryForm categoryForm) {
        CategoryDto categoryDto = categoryService.createCategory(categoryForm);

        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }


    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@Positive @PathVariable Long id) throws CategoryException {
        CategoryDto categoryDto = categoryService.getCategory(id);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Admin
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryForm categoryForm, @Positive @PathVariable Long id)
            throws CategoryException {
        CategoryDto categoryDto = categoryService.updateCategory(categoryForm, id);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @Admin
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@Positive @PathVariable Long id, @PositiveOrZero @RequestParam Integer version)
            throws CategoryException {
        categoryService.deleteCategory(id, version);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
