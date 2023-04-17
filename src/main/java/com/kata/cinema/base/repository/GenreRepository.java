package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getGenreById(Long id);
}
