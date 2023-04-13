package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.CollectionCategories;

import java.util.List;

public interface CollectionCategoryService {

    List<CollectionCategories> getAllCollectionCategories();

    CollectionCategories getCollectionCategoriesById(Long id);

    void deleteCollectionCategories(CollectionCategories collectionCategories);


    CollectionCategories updateCollectionCategories(Long id, String name);



    void createCollectionCategories(CollectionCategories collectionCategories);

    boolean isCollectionCategoriesExistsById(Long id);

    void updateCollectionCategories(CollectionCategories collectionCategories);
}