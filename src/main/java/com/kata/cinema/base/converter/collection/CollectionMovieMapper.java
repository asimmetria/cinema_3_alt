package com.kata.cinema.base.converter.collection;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entitys.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = ConfigMapper.class)
public interface CollectionMovieMapper extends DtoMapper<MovieResponseDto, Movie> {
    @Override
    default MovieResponseDto toDto(Movie movie) {
        MovieResponseDto responseDto = new MovieResponseDto();
        List<String> countries = movie.getCountry().stream().map(c -> c.getName()).toList();
        List<String> genreList = movie.getGenre().stream().map(g -> g.getName()).toList();

        int countScore = movie.getScores().size();
        Double avgScore = (double) movie.getScores().stream().mapToInt(score -> score.getScore()).sum() / countScore;


        responseDto.setId(movie.getId());
        responseDto.setName(movie.getName());
        responseDto.setOriginalName(movie.getOriginalName());
        responseDto.setTime(movie.getTime());
        responseDto.setDateRelease(movie.getDateRelease());
        responseDto.setPreviewUrl(movie.getPreviewUrl());
        responseDto.setCountries(countries);
        responseDto.setGenres(genreList);
        responseDto.setAvgScore(Math.ceil(avgScore * 100) / 100);
        responseDto.setCountScore((long) countScore);

        return responseDto;
    }
}

