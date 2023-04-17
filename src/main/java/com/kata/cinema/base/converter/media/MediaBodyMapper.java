package com.kata.cinema.base.converter.media;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.MediaBodyResponseDto;
import com.kata.cinema.base.models.entitys.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ConfigMapper.class)
public interface MediaBodyMapper extends DtoMapper<MediaBodyResponseDto, Media> {

    @Override
    @Mapping(target = "countComment", expression = "java((long) media.getComments().size())")
    @Mapping(target = "author", expression = "java(media.getAuthor().getFullName())")
    MediaBodyResponseDto toDto(Media media);
}
