package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Excertion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExcertionRepository extends JpaRepository<Excertion, Long> {
    Excertion getExcertionById(Long id);

    List<Excertion> getExcertionsByMovieId(Long id, Pageable pageable);

    List<Excertion> getExcertionsByPersonId(Long id, Pageable pageable);

    boolean existsExcertionById(Long id);
}
