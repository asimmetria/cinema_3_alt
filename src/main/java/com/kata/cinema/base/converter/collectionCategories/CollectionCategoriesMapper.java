package com.kata.cinema.base.converter.collectionCategories;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionCategoriesResponseDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(config = ConfigMapper.class)
@Component
public interface CollectionCategoriesMapper extends DtoMapper<CollectionCategoriesResponseDto, CollectionCategories>, EntityMapper<CollectionCategoriesRequestDto, CollectionCategories> {
}


