package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.ProductionStudios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionStudiosRepository extends JpaRepository<ProductionStudios, Long> {
}
