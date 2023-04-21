package com.kata.cinema.base.converter.excertion;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.entitys.Excertion;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface ExcertionMapper extends DtoMapper<ExcertionResponseDto, Excertion>, EntityMapper<ExcertionRequestDto, Excertion> {
}
