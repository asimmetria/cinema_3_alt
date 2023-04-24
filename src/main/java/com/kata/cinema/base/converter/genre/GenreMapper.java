package com.kata.cinema.base.converter.genre;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.entitys.Genre;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface GenreMapper extends DtoMapper<GenreResponseDto, Genre> {
}
