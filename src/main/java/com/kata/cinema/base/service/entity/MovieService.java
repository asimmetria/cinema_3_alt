package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entitys.Movie;
import java.util.List;

public interface MovieService {
    Movie getMovie(Long id);

    void save(Long id, MovieRequestDto movieDto);

    void deleteById(Long id);

    boolean isExist(Long id);

    List<Movie> getMoviesByIds(List<Long> ids);

    List<Movie> findAll();
}
