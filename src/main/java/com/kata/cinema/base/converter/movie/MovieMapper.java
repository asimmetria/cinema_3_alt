package com.kata.cinema.base.converter.movie;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.converter.genre.GenreMapper;
import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ConfigMapper.class, uses = {GenreMapper.class})
public interface MovieMapper extends DtoMapper<MovieViewResponseDto, Movie> {

    @Override
    @Mapping(source = "genre", target = "genres")
    @Mapping(target = "score", expression = "java(movie.getScores().stream().map(e -> e.getScore()).mapToInt(e -> e).average().orElse(0))")
    @Mapping(target = "countScore", expression = "java((long) movie.getScores().size())")
    @Mapping(target = "casts", ignore = true)
    MovieViewResponseDto toDto(Movie movie);
}
