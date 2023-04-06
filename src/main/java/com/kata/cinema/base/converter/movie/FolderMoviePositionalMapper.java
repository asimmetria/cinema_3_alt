package com.kata.cinema.base.converter.movie;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.FolderMoviePositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;

import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface FolderMoviePositionalMapper extends DtoMapper<FolderMoviePositionalResponseDto, FolderMoviePositional>,
        EntityMapper<FolderMoviePositionalRequestDto, FolderMoviePositional>  {
}
