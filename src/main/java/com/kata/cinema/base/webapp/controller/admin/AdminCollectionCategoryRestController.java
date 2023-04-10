package com.kata.cinema.base.webapp.controller.admin;

import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.dto.response.CategoryResponseDto;
import com.kata.cinema.base.models.dto.validator.impl.CategoryRequestDtoValidatorImpl;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.facade.CollectionCategoryFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/collections/categories")
@Validated
public class AdminCollectionCategoryRestController {

    private final CollectionCategoryFacade categoryFacade;
    private final CategoryRequestDtoValidatorImpl categoryRequestDtoValidator;

    public AdminCollectionCategoryRestController(CollectionCategoryFacade categoryFacade,
                                                 CategoryRequestDtoValidatorImpl categoryRequestDtoValidator) {
        this.categoryFacade = categoryFacade;
        this.categoryRequestDtoValidator = categoryRequestDtoValidator;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CollectionCategories> categories = categoryFacade.getAllCategories();
        List<CategoryResponseDto> categoryResponseDto = categories.stream()
                .map(category -> new CategoryResponseDto(category.getId(), category.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        CollectionCategories category = categoryFacade.getCategoryById(id);
        categoryFacade.deleteCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestParam String name) {
        categoryFacade.updateCategory(id, name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryFacade.createCategory(categoryRequestDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}