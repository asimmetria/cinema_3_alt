package com.kata.cinema.base.service.facade;

import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.dto.validator.CategoryRequestDtoValidator;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectionCategoryFacade {

    private final CollectionCategoryService categoryService;
    private final CategoryRequestDtoValidator categoryRequestDtoValidator;

    public CollectionCategoryFacade(CollectionCategoryService categoryService,
                                    CategoryRequestDtoValidator categoryRequestDtoValidator) {
        this.categoryService = categoryService;
        this.categoryRequestDtoValidator = categoryRequestDtoValidator;
    }

    public CollectionCategories getCategoryById(Long id) {
        if (!categoryService.isCategoryExistById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        return categoryService.getCategoryById(id);
    }

    public List<CollectionCategories> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public void deleteCategory(CollectionCategories category) {
        if (!categoryService.isCategoryExistById(category.getId())) {
            throw new EntityNotFoundException("Category with id " + category.getId() + " not found");
        }
        categoryService.deleteCategory(category);
    }

    public void updateCategory(Long id, String name) {
        if (!categoryService.isCategoryExistById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        categoryRequestDtoValidator.validate(new CategoryRequestDto(name), null);
        categoryService.updateCategory(id, name);
    }

    public void createCategory(String name) {
        categoryRequestDtoValidator.validate(new CategoryRequestDto(name), null);
        categoryService.createCategory(name);
    }

}