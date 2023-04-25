package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.CollectionCategories;

import java.util.List;

public interface CollectionCategoryService {

    List<CollectionCategories> getAllCollectionCategories();

    CollectionCategories getCollectionCategoriesById(Long id);

    void deleteCollectionCategories(CollectionCategories collectionCategories);

    void createCollectionCategories(CollectionCategories collectionCategories);

    void updateCollectionCategories(CollectionCategories collectionCategories);
}