package com.kata.cinema.base.converter.folder;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.entitys.FolderPerson;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface FolderPersonMapper extends DtoMapper<FolderPersonResponseDto, FolderPerson>, EntityMapper<FolderRequestDto, FolderPerson> {
}
