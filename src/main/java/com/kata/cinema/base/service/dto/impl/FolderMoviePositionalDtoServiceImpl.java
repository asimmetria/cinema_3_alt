package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.service.dto.FolderMoviePositionalDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderMoviePositionalDtoServiceImpl implements FolderMoviePositionalDtoService {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;

    @Override
    public FolderMoviePositionalResponseDto getByFolderIdAndMovieId(Long folderId, Long movieId) {
        return folderMoviePositionalRepository.getByFolderIdAndMovieId(folderId, movieId);
    }

}
