package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.FolderPersonResponseDto;

import java.util.List;

public interface UserFolderService {
    List<FolderMovieResponseDto> getFolderMovies();
    List<FolderPersonResponseDto> getFolderPersons();

    void createFolderPersons(FolderPersonResponseDto folderPersonResponseDto);
    void createFolderMovies(FolderMovieResponseDto folderMovieResponseDto);
    void deleteFolderPersons(FolderPersonResponseDto folderPersonResponseDto);
    void deleteFolderMovies(FolderMovieResponseDto folderMovieResponseDto);

}
