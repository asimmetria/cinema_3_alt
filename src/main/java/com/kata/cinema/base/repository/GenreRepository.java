package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getGenreById(Long id);

    @Query("FROM Genre g WHERE g.id IN :ids")
    Set<Genre> getAllGenresByIds(@Param("ids") List<Long> ids);

    @Query("SELECT g.name FROM Movie m join m.genre g where m.id = :id")
    List<String> getGenreByMovieId(@Param("id") Long id);

}
