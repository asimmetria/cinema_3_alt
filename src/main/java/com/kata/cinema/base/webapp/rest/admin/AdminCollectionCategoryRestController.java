package com.kata.cinema.base.webapp.rest.admin;

import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionCategoriesResponseDto;
import com.kata.cinema.base.models.dto.validator.CollectionCategoriesRequestDtoValidator;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.webapp.facade.collection.CollectionCategoryFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/collections/categories")
@Validated
public class AdminCollectionCategoryRestController {
    private final CollectionCategoryFacade collectionCategoryFacade;

    private final CollectionCategoriesRequestDtoValidator collectionCategoriesRequestDtoValidator;

    public AdminCollectionCategoryRestController(CollectionCategoryFacade collectionCategoryFacade,
                                                 CollectionCategoriesRequestDtoValidator collectionCategoriesRequestDtoValidator) {
        this.collectionCategoryFacade = collectionCategoryFacade;
        this.collectionCategoriesRequestDtoValidator = collectionCategoriesRequestDtoValidator;
    }

    @GetMapping
    public ResponseEntity<List<CollectionCategoriesResponseDto>> getCollectionCategories() {
        List<CollectionCategories> collectionCategories = collectionCategoryFacade.getAllCollectionCategories();
        List<CollectionCategoriesResponseDto> collectionCategoriesResponseDto = collectionCategories.stream()
                .map(CollectionCategoriesResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(collectionCategoriesResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionCategories(@PathVariable Long id) {
        collectionCategoryFacade.deleteCollectionCategories(collectionCategoryFacade.getCollectionCategoriesById(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCollectionCategories(@PathVariable Long id, @RequestParam String name) {
        CollectionCategories collectionCategories = collectionCategoryFacade.getCollectionCategoriesById(id);
        collectionCategories.setName(name);
        collectionCategoryFacade.updateCollectionCategories(collectionCategories);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createCollectionCategories(@Validated @RequestBody CollectionCategoriesRequestDto collectionCategoriesRequestDto) {
        collectionCategoryFacade.createCollectionCategories(collectionCategoriesRequestDto);
        return ResponseEntity.ok().build();
    }
}