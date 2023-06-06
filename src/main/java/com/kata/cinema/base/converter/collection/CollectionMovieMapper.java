package com.kata.cinema.base.converter.collection;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entitys.*;
import org.mapstruct.Mapper;


@Mapper(config = ConfigMapper.class)
public interface CollectionMovieMapper extends DtoMapper<MovieResponseDto, Movie> {


}

