package com.kata.cinema.base.webapp.facade.admin.impl;

import com.kata.cinema.base.converter.collectionCategories.CollectionCategoriesMapper;
import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.models.dto.validator.CollectionCategoriesRequestDtoValidator;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//TODO создать интефрейс
public class CollectionCategoryFacade {
    private final CollectionCategoryService collectionCategoryService;
    private final CollectionCategoriesRequestDtoValidator collectionCategoriesRequestDtoValidator;
    private final CollectionCategoriesMapper collectionCategoriesMapper;

    public CollectionCategoryFacade(CollectionCategoryService collectionCategoryService,
                                    CollectionCategoriesRequestDtoValidator collectionCategoriesRequestDtoValidator, CollectionCategoriesMapper collectionCategoriesMapper) {
        this.collectionCategoryService = collectionCategoryService;
        this.collectionCategoriesRequestDtoValidator = collectionCategoriesRequestDtoValidator;
        this.collectionCategoriesMapper = collectionCategoriesMapper;
    }


    public CollectionCategories getCollectionCategoriesById(Long id) {
        return collectionCategoryService.getCollectionCategoriesById(id);
    }

    public List<CollectionCategories> getAllCollectionCategories() {
        return collectionCategoryService.getAllCollectionCategories();
    }

    public void deleteCollectionCategories(CollectionCategories collectionCategories) {
        collectionCategoryService.deleteCollectionCategories(collectionCategories);
    }

    public void updateCollectionCategories(CollectionCategories collectionCategories) {
        collectionCategoryService.updateCollectionCategories(collectionCategories);
    }

    public void createCollectionCategories(CollectionCategoriesRequestDto collectionCategoriesRequestDto) throws ValidationException {
        collectionCategoriesRequestDto.validate(collectionCategoriesRequestDtoValidator);
        collectionCategoryService.createCollectionCategories(collectionCategoriesMapper.toEntity(collectionCategoriesRequestDto));
    }
}