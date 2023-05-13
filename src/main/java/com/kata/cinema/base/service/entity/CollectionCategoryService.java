package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.CollectionCategoriesResponseDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;

import java.util.List;

public interface CollectionCategoryService {

    List<CollectionCategories> getAllCollectionCategories();

    CollectionCategories getCollectionCategoriesById(Long id);

    void deleteCollectionCategoriesById(Long id);

    void createCollectionCategories(CollectionCategories collectionCategories);

    void updateCollectionCategoriesNameById(Long id, String name);
}