package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.repository.CollectionCategoryRepository;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionCategoryServiceImpl implements CollectionCategoryService {

    private final CollectionCategoryRepository categoryRepository;

    public CollectionCategoryServiceImpl(CollectionCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CollectionCategories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CollectionCategories getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(CollectionCategories category) {
        categoryRepository.delete(category);
    }

    @Override
    public void updateCategory(CollectionCategories category) {
        categoryRepository.save(category);
    }

    @Override
    public void createCategory(String name) {
        CollectionCategories category = new CollectionCategories();
        category.setName(name);
        categoryRepository.save(category);
    }
}
