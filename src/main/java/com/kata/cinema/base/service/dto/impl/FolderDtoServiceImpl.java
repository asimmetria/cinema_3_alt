package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.repository.UserFolderMovieRepository;
import com.kata.cinema.base.repository.UserFolderPersonRepository;
import com.kata.cinema.base.service.dto.FolderDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderDtoServiceImpl implements FolderDtoService {

    private final UserFolderMovieRepository userFolderMovieRepository;
    private final UserFolderPersonRepository userFolderPersonRepository;

    @Override
    public List<FolderMovieResponseDto> getMovieFoldersByUserId(Long userId) {
        return userFolderMovieRepository.getFolderMovieByUserId(userId);
    }

    @Override
    public List<FolderPersonResponseDto> getPersonFoldersByUserId(Long userId) {
        return userFolderPersonRepository.getFolderPersonByUserId(userId);
    }
}
