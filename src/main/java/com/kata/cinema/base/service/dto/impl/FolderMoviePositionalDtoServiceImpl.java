package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.service.dto.FolderMoviePositionalDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderMoviePositionalDtoServiceImpl implements FolderMoviePositionalDtoService {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;

    @Override
    public FolderMoviePositionalResponseDto getFolderMoviePositionalByFolderAndMovie(Long folderId, Long movieId) {
        return folderMoviePositionalRepository.getFolderMoviePositionalByFolderAndMovie(folderId, movieId);
    }

    @Override
    public List<FolderMoviePositionalResponseDto> getAll() {
        return folderMoviePositionalRepository.getAll();
    }


}
