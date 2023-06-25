package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Genre;

import java.util.List;
import java.util.Set;

public interface GenreService {
    Genre getGenre(Long id);

    Set<Genre> getGenresByIds(List<Long> ids);

    void save(Genre genre);

    void deleteById(Long id);

    List<Genre> findAll();
}
