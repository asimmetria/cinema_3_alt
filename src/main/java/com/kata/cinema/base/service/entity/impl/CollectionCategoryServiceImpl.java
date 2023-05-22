package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.collectionCategories.CollectionCategoriesMapper;
import com.kata.cinema.base.models.dto.response.CollectionCategoriesResponseDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.repository.CollectionCategoryRepository;
import com.kata.cinema.base.service.entity.CollectionCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionCategoryServiceImpl implements CollectionCategoryService {

    private final CollectionCategoryRepository collectionCategoryRepository;

    public CollectionCategoryServiceImpl(CollectionCategoryRepository collectionCategoryRepository) {
        this.collectionCategoryRepository = collectionCategoryRepository;
    }

    @Override
    public List<CollectionCategories> getAllCollectionCategories() {
        return collectionCategoryRepository.findAll();
    }

    @Override
    public CollectionCategories getCollectionCategoriesById(Long id) {
        return collectionCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCollectionCategoriesById(Long id) {
        if (!collectionCategoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        collectionCategoryRepository.deleteById(id);
    }

    @Override
    public void createCollectionCategories(CollectionCategories collectionCategories) {
        collectionCategoryRepository.save(collectionCategories);
    }


    @Override
    public void updateCollectionCategoriesNameById(Long id, String name) {
        CollectionCategories collectionCategories = collectionCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
        collectionCategories.setName(name);
        collectionCategoryRepository.save(collectionCategories);
    }
}
