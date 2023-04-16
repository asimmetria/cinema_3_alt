package com.kata.cinema.base.webapp.facade.movie;

import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;

public interface MovieServiceFacade {
    MovieViewResponseDto getMovie(Long id);

    void delete(Long id);
}
