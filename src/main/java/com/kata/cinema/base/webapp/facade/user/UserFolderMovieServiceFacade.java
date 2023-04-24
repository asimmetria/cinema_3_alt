package com.kata.cinema.base.webapp.facade.user;

import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;

import java.util.List;

public interface UserFolderMovieServiceFacade {

    List<FolderMovieResponseDto> getFolderMoviesByUserId(long userId);

    void createFolderMovies(FolderRequestDto folderRequestDto, Long userId);

    void deleteFolderById(long id);
}
