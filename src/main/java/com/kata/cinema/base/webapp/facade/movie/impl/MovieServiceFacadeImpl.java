package com.kata.cinema.base.webapp.facade.movie.impl;

import com.kata.cinema.base.converter.movie.MovieMapper;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.webapp.facade.movie.MovieServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieServiceFacadeImpl implements MovieServiceFacade {
    private final MovieService movieService;
    private final GenreService genreService;
    private final MovieMapper movieMapper;

    @Override
    public void createMovie(MovieRequestDto movieDto) {
        List<Genre> genres = movieDto.getGenreIds().stream().map(genreService::getGenre).toList();
        Movie movie = movieMapper.toEntity(movieDto);
        movie.setGenre(genres);
        movieService.save(movie);
    }

    @Override
    public void updateMovie(Long id, MovieRequestDto movieDto) {
        List<Genre> genres = movieDto.getGenreIds().stream().map(genreService::getGenre).toList();
        Movie movie = movieMapper.toEntity(movieDto);
        movie.setGenre(genres);

        boolean exist = movieService.isExist(id);
        if (exist) {
            movie.setId(id);
        }

        movieService.save(movie);
    }
}
