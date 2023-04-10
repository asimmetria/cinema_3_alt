package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.CollectionCategories;

import java.util.List;

public interface CollectionCategoryService {

    List<CollectionCategories> getAllCategories();

    CollectionCategories getCategoryById(Long id);

    void deleteCategory(CollectionCategories category);


    CollectionCategories updateCategory(Long id, String name);



    void createCategory(String name);

    boolean isCategoryExistById(Long id);
}