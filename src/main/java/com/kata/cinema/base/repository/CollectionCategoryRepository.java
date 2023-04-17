package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.CollectionCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionCategoryRepository extends JpaRepository<CollectionCategories, Long> {
}