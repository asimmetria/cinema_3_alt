package com.kata.cinema.base.webapp.facade.admin;

import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import jakarta.validation.ValidationException;

import java.util.List;

public interface CollectionCategoryFacade {

    List<CollectionCategories> getAllCollectionCategories();

    void deleteCollectionCategoriesById(Long id);

    void updateCollectionCategoriesNameById(Long id, String name);

    void createCollectionCategories(CollectionCategoriesRequestDto collectionCategoriesRequestDto) throws ValidationException;

}
