package com.kata.cinema.base.converter.media;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.MediaRequestDto;
import com.kata.cinema.base.models.dto.response.MediaTitleResponseDto;
import com.kata.cinema.base.models.entitys.Media;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface MediaMapper extends DtoMapper<MediaTitleResponseDto, Media>, EntityMapper<MediaRequestDto, Media> {
}
