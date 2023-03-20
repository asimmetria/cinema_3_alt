package com.kata.cinema.base.converter.production;

import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.models.entitys.ProductionStudios;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface ProductionStudioMapper extends EntityMapper<ProductionStudiosRequestDto, ProductionStudios>{
}
