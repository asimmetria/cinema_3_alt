package com.kata.cinema.base.webapp.controller.admin;

import com.kata.cinema.base.models.dto.response.CategoryResponseDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/collections/categories")
public class AdminCollectionCategoryRestController {

    private final CollectionCategoryService categoryService;

    public AdminCollectionCategoryRestController(CollectionCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDto> getCategories() {
        List<CollectionCategories> categories = categoryService.getAllCategories();
        return categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        CollectionCategories category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        categoryService.deleteCategory(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id, @RequestParam String name) {
        CollectionCategories category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        category.setName(name);
        categoryService.updateCategory(category);
    }

    @PostMapping
    public void createCategory(@RequestParam String name) {
        categoryService.createCategory(name);
    }
}