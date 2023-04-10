package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

}
