package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.CollectionCategories;

import java.util.List;

public interface CollectionCategoryService {

    List<CollectionCategories> getAllCategories();

    CollectionCategories getCategoryById(Long id);

    void deleteCategory(CollectionCategories category);

    void updateCategory(CollectionCategories category);

    void createCategory(String name);
}