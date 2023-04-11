package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;

public interface FolderMoviePositionalDtoService {

    FolderMoviePositionalResponseDto getByFolderIdAndMovieId(Long folderId, Long movieId);

}
