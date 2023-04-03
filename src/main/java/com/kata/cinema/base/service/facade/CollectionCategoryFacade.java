package com.kata.cinema.base.service.facade;

import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectionCategoryFacade {

    private final CollectionCategoryService categoryService;

    public CollectionCategoryFacade(CollectionCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CollectionCategories getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    public List<CollectionCategories> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public void deleteCategory(CollectionCategories category) {
        categoryService.deleteCategory(category);
    }

    public void updateCategory(CollectionCategories category) {
        categoryService.updateCategory(category);
    }

    public void createCategory(String name) {
        categoryService.createCategory(name);
    }
}