package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Movie;

public interface MovieService {
    Movie getMovie(Long id);

    void save(Movie movie);

    void deleteById(Long id);
}
