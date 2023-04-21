package com.kata.cinema.base.webapp.facade.movie.impl;

import com.kata.cinema.base.converter.movie.MovieMapper;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entitys.Country;
import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.service.entity.CountryService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.converter.cast.CastMapper;
import com.kata.cinema.base.converter.movie.MovieMapper;
import com.kata.cinema.base.converter.movie.MoviePersonMapper;
import com.kata.cinema.base.models.dto.response.CastResponseDto;
import com.kata.cinema.base.models.dto.response.MoviePersonResponseDto;
import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.models.entitys.Cast;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.Person;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.webapp.facade.movie.MovieServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MovieServiceFacadeImpl implements MovieServiceFacade {
    private final MovieService movieService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final MovieMapper movieMapper;
    private final MoviePersonMapper moviePersonMapper;
    private final CastMapper castMapper;

    @Override
    public MovieViewResponseDto getMovie(Long id) {
        Movie movie = movieService.getMovie(id);
        MovieViewResponseDto movieDto = movieMapper.toDto(movie);
        List<CastResponseDto> castsDto = movie.getCast().stream().map(castMapper::toDto).distinct().toList();
        List<Person> persons = movie.getCast().stream().map(Cast::getPerson).toList();
        List<MoviePersonResponseDto> personsDto = new ArrayList<>();

        for (Person person : persons) {
            personsDto.addAll(moviePersonMapper.toDto(person));
        }

        for (CastResponseDto castDto : castsDto) {
            List<MoviePersonResponseDto> totalPersonsDto = personsDto.stream()
                    .filter(e -> e.getProfessionId().equals(castDto.getProfessionId()))
                    .distinct()
                    .toList();
            castDto.setPersons(totalPersonsDto);
        }

        movieDto.setCasts(castsDto);
        return movieDto;
    }

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

    @Override
    public void delete(Long id) {
        movieService.deleteById(id);
    }
}
