package com.kata.cinema.base.converter.person;

import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface FolderPersonPositionalMapper extends EntityMapper<FolderPersonPositionalResponseDto, FolderPersonPositional> {
}
