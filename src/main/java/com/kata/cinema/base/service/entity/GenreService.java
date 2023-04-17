package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Genre;

public interface GenreService {
    Genre getGenre(Long id);

    void save(Genre genre);

    void deleteById(Long id);
}
