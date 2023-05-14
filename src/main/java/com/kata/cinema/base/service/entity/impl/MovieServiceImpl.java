package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.movie.MovieMapper;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entitys.Country;
import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.CountryService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    private final GenreService genreService;
    private final CountryService countryService;
    private final MovieMapper movieMapper;

    @Override
    public Movie getMovie(Long id) {
        return movieRepository.getMovieById(id);
    }

    @Override
    public List<Movie> getMoviesByIds(List<Long> ids) {
        return movieRepository.findAllById(ids);
    }

    @Override
    public void save(Long id, MovieRequestDto movieDto) {
        Set<Genre> genres = genreService.getGenresByIds(movieDto.getGenreIds());
        Set<Country> countries = countryService.getCountriesByIds(movieDto.getCountryIds());
        Movie movie = movieMapper.toEntity(movieDto);
        movie.setGenre(genres);
        movie.setCountry(countries);

        if (isExist(id)) movie.setId(id);

        movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return movieRepository.existsMovieById(id);
    }
}
