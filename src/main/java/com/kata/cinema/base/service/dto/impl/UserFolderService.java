package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;

import java.util.List;

public interface UserFolderService {
    List<FolderMovieResponseDto> getMovieFoldersByUserId(Long userId);
    List<FolderPersonResponseDto> getPersonFoldersByUserId(Long userId);

    void createFolderPersons(FolderRequestDto folderRequestDto, Long userId);
    void createFolderMovies(FolderRequestDto folderRequestDto, Long userId);
    void deleteFolderPersons(FolderPersonResponseDto folderPersonResponseDto);
    void deleteFolderMovies(FolderMovieResponseDto folderMovieResponseDto);

}
