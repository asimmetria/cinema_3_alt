package com.kata.cinema.base.converter.collection;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = ConfigMapper.class)
public interface CollectionMapper extends EntityMapper<CollectionRequestDto, Collection>,
        DtoMapper<CollectionResponseDto, Collection> {
}
