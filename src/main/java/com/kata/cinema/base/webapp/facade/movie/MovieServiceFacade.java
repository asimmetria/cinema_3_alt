package com.kata.cinema.base.webapp.facade.movie;

import com.kata.cinema.base.models.dto.request.MovieRequestDto;

public interface MovieServiceFacade {
    void createMovie(MovieRequestDto movieDto);

    void updateMovie(Long id, MovieRequestDto movieDto);
}
