package com.kata.cinema.base.converter.cast;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.CastResponseDto;
import com.kata.cinema.base.models.entitys.Cast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ConfigMapper.class)
public interface CastMapper extends DtoMapper<CastResponseDto, Cast> {

    @Override
    @Mapping(target = "movieId", expression = "java(cast.getMovie().getId())")
    @Mapping(target = "professionId", expression = "java(cast.getProfession().getId())")
    @Mapping(target = "professionName", expression = "java(cast.getProfession().getName())")
    @Mapping(target = "persons", ignore = true)
    CastResponseDto toDto(Cast cast);
}
