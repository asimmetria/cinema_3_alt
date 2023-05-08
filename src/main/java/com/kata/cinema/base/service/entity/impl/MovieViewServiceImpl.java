package com.kata.cinema.base.service.entity.impl;

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
import com.kata.cinema.base.service.entity.MovieViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class MovieViewServiceImpl implements MovieViewService {
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final MoviePersonMapper moviePersonMapper;
    private final CastMapper castMapper;
    @Override
    public MovieViewResponseDto getMovieViewResponseDto(Long id) {

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
}
