package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Excertion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcertionRepository extends JpaRepository<Excertion, Long> {
    Excertion getExcertionById(Long id);

    List<Excertion> getExcertionsByMovieId(Long id);

    List<Excertion> getExcertionsByPersonId(Long id);

    boolean existsById(Long id);
}
