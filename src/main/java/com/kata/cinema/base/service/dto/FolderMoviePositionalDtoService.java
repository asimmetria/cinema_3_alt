package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;

import java.util.List;

public interface FolderMoviePositionalDtoService {

    FolderMoviePositionalResponseDto getFolderMoviePositionalByFolderAndMovie(Long folderId, Long movieId);

    List<FolderMoviePositionalResponseDto> getAll();
}
