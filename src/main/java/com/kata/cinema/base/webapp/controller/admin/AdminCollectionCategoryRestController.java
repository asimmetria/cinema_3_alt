package com.kata.cinema.base.webapp.controller.admin;

import com.kata.cinema.base.models.dto.ErrorResponse;
import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.dto.response.CategoryResponseDto;
import com.kata.cinema.base.models.dto.validator.CategoryRequestDtoValidator;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.facade.CollectionCategoryFacade;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/collections/categories")
@Validated
public class AdminCollectionCategoryRestController {

    private final CollectionCategoryFacade categoryFacade;
    private final CategoryRequestDtoValidator categoryRequestDtoValidator;

    public AdminCollectionCategoryRestController(CollectionCategoryFacade categoryFacade,
                                                 CategoryRequestDtoValidator categoryRequestDtoValidator) {
        this.categoryFacade = categoryFacade;
        this.categoryRequestDtoValidator = categoryRequestDtoValidator;
    }

    private boolean isExistById(Long id) {
        return categoryFacade.getCategoryById(id) != null;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CollectionCategories> categories = categoryFacade.getAllCategories();
        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!isExistById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        CollectionCategories category = categoryFacade.getCategoryById(id);
        categoryFacade.deleteCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestParam String name) {
        if (!isExistById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        CollectionCategories category = categoryFacade.getCategoryById(id);
        category.setName(name);
        categoryFacade.updateCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto,
                                               BindingResult bindingResult) {
        categoryRequestDtoValidator.validate(categoryRequestDto, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            ErrorResponse errorResponse = new ErrorResponse("Validation failed", HttpStatus.BAD_REQUEST, 400, new Date());
            errorResponse.setText(errors.toString());
            return ResponseEntity.badRequest().body(null);
        }
        categoryFacade.createCategory(categoryRequestDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}