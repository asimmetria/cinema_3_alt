package com.kata.cinema.base.webapp.facade.movie.impl;

import com.kata.cinema.base.converter.movie.MovieMapper;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entitys.Country;
import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.service.entity.CountryService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.webapp.facade.movie.MovieServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class MovieServiceFacadeImpl implements MovieServiceFacade {
    private final MovieService movieService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final MovieMapper movieMapper;

    @Override
    public void createMovie(MovieRequestDto movieDto) {
        Set<Genre> genres = genreService.getGenresByIds(movieDto.getGenreIds());
        Set<Country> countries = countryService.getCountriesByIds(movieDto.getCountryIds());
        Movie movie = movieMapper.toEntity(movieDto);
        movie.setGenre(genres);
        movie.setCountry(countries);
        movieService.save(movie);
    }

    @Override
    public void updateMovie(Long id, MovieRequestDto movieDto) {
        Set<Genre> genres = genreService.getGenresByIds(movieDto.getGenreIds());
        Set<Country> countries = countryService.getCountriesByIds(movieDto.getCountryIds());
        Movie movie = movieMapper.toEntity(movieDto);
        movie.setGenre(genres);
        movie.setCountry(countries);

        boolean exist = movieService.isExist(id);
        if (exist) {
            movie.setId(id);
        }

        movieService.save(movie);
    }
}
