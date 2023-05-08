package com.kata.cinema.base.webapp.facade.admin.impl;

import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.MovieViewService;
import com.kata.cinema.base.validation.MovieValidation;
import com.kata.cinema.base.webapp.facade.admin.MovieServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MovieServiceFacadeImpl implements MovieServiceFacade {
    private final MovieService movieService;
    private final MovieValidation movieValidation;
    private final MovieViewService movieViewService;

    @Override
    public MovieViewResponseDto getMovie(Long id) {
        return movieViewService.getMovieViewResponseDto(id);
    }

    @Override
    public void createMovie(MovieRequestDto movieDto) {
        movieService.save(null, movieDto);
    }

    @Override
    public void updateMovie(Long id, MovieRequestDto movieDto) {
        movieValidation.isExistMovieById(id);
        movieService.save(id, movieDto);
    }

    @Override
    public void delete(Long id) {
        movieValidation.isExistMovieById(id);
        movieService.deleteById(id);
    }

}


