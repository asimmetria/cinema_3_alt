package com.kata.cinema.base.webapp.facade.movie;

import com.kata.cinema.base.models.dto.request.FolderMoviePositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;

import java.util.List;

public interface UserFolderMoviePositionalServiceFacade {
    FolderMoviePositionalResponseDto getFolderMoviePositionalByFolderAndMovie(Long folderId, Long movieId);

    void addMovieToFolder(FolderMoviePositionalRequestDto movieRequestDto);

    void deleteFolderMoviePositionalById(Long id);

    List<FolderMoviePositionalResponseDto> getAll();

    void updateMovie(FolderMoviePositionalRequestDto movieRequestDto);

}
