package com.bonkAndrzej.iNeedProgrammers.category;

import com.bonkAndrzej.iNeedProgrammers.category.dto.CategoryDto;
import com.bonkAndrzej.iNeedProgrammers.category.dto.CategoryForm;
import com.bonkAndrzej.iNeedProgrammers.category.exception.CategoryException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service @Transactional
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryForm categoryForm) {
        Category category = new Category();
        category.setName(categoryForm.getName());

        Category categoryAfterSave = categoryRepository.save(category);
        return new CategoryDto(categoryAfterSave);
    }

    public CategoryDto updateCategory(CategoryForm categoryForm, Long id) throws CategoryException {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryException("Category not found with given id " + id));

        if (!category.getVersion().equals(categoryForm.getVersion()))
            throw new CategoryException("OptimisticLockException - wrong category version " + categoryForm.getVersion() +
                    "\nExpected " + category.getVersion());

        category.setName(categoryForm.getName());
        Category categoryAfterUpdate = categoryRepository.save(category);
        return new CategoryDto(categoryAfterUpdate);
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategory(Long id) throws CategoryException {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryException("Category not found with given id " + id));

        return new CategoryDto(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryDto::new).collect(Collectors.toList());
    }

    public void deleteCategory(Long id, Integer version) throws CategoryException {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryException("Category not found with given id " + id));

        if (!category.getVersion().equals(version))
            throw new CategoryException("OptimisticLockException - wrong category version " + version +
                    "\nExpected " + category.getVersion());

        categoryRepository.delete(category);
    }


}
