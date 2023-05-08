package com.kata.cinema.base.webapp.facade.admin;

import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;

public interface MovieServiceFacade {

    MovieViewResponseDto getMovie(Long id);

    void createMovie(MovieRequestDto movieDto);

    void updateMovie(Long id, MovieRequestDto movieDto);

    void delete(Long id);
}
