package com.kata.cinema.base.webapp.facade.admin.impl;

import com.kata.cinema.base.converter.collectionCategories.CollectionCategoriesMapper;
import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.models.dto.validator.CollectionCategoriesRequestDtoValidator;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import com.kata.cinema.base.webapp.facade.admin.CollectionCategoryFacade;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectionCategoryFacadeImpl implements CollectionCategoryFacade {
    private final CollectionCategoryService collectionCategoryService;
    private final CollectionCategoriesRequestDtoValidator collectionCategoriesRequestDtoValidator;
    private final CollectionCategoriesMapper collectionCategoriesMapper;

    public CollectionCategoryFacadeImpl(CollectionCategoryService collectionCategoryService,
                                        CollectionCategoriesRequestDtoValidator collectionCategoriesRequestDtoValidator, CollectionCategoriesMapper collectionCategoriesMapper) {
        this.collectionCategoryService = collectionCategoryService;
        this.collectionCategoriesRequestDtoValidator = collectionCategoriesRequestDtoValidator;
        this.collectionCategoriesMapper = collectionCategoriesMapper;
    }

    public List<CollectionCategories> getAllCollectionCategories() {
        return collectionCategoryService.getAllCollectionCategories();
    }

    public void deleteCollectionCategoriesById (Long id) {
        collectionCategoryService.deleteCollectionCategoriesById(id);
    }

    public void updateCollectionCategoriesNameById (Long id, String name) {
        collectionCategoryService.updateCollectionCategoriesNameById(id, name);
    }

    public void createCollectionCategories(CollectionCategoriesRequestDto collectionCategoriesRequestDto) throws ValidationException {
        collectionCategoriesRequestDto.validate(collectionCategoriesRequestDtoValidator);
        collectionCategoryService.createCollectionCategories(collectionCategoriesMapper.toEntity(collectionCategoriesRequestDto));
    }
}