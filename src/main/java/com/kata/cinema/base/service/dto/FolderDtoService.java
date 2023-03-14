package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;

import java.util.List;

public interface FolderDtoService {

    List<FolderMovieResponseDto> getMovieFoldersByUserId(Long userId);
    List<FolderPersonResponseDto> getPersonFoldersByUserId(Long userId);


}
