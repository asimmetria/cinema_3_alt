package com.kata.cinema.base.converter.movie;

import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entitys.Movie;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface MovieMapper extends EntityMapper<MovieRequestDto, Movie> {
}
