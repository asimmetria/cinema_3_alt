package com.kata.cinema.base.webapp.controller.admin;

import com.kata.cinema.base.models.dto.response.CategoryResponseDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CollectionCategories> categories = categoryService.getAllCategories();
        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        CollectionCategories category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        categoryService.deleteCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestParam String name) {
        CollectionCategories category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        category.setName(name);
        categoryService.updateCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestParam String name) {
        categoryService.createCategory(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}