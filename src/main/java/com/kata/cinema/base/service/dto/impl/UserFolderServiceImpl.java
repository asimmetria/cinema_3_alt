package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.repository.UserFolderMovieRepository;
import com.kata.cinema.base.repository.UserFolderPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFolderServiceImpl implements UserFolderService{

    private final UserFolderMovieRepository userFolderMovieRepository;
    private final UserFolderPersonRepository userFolderPersonRepository;
    private final MappingUtils mappingUtils;

    public UserFolderServiceImpl(UserFolderMovieRepository userFolderMovieRepository, UserFolderPersonRepository userFolderPersonRepository, MappingUtils mappingUtils) {
        this.userFolderMovieRepository = userFolderMovieRepository;
        this.userFolderPersonRepository = userFolderPersonRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public List<FolderMovieResponseDto> getMovieFoldersByUserId(Long userId) {
        return userFolderMovieRepository.findById(userId).stream().map(mappingUtils::mapToMovieDto).collect(Collectors.toList());
    }

    @Override
    public List<FolderPersonResponseDto> getPersonFoldersByUserId(Long userId) {
        return userFolderPersonRepository.findById(userId).stream().map(mappingUtils::mapToPersonDto).collect(Collectors.toList());
    }

    @Override
    public void createFolderPersons(FolderRequestDto folderRequestDto, Long userId) {
        userFolderPersonRepository.save(mappingUtils.mapRToPersonEntity(folderRequestDto));
    }

    @Override
    public void createFolderMovies(FolderRequestDto folderRequestDto, Long userId) {
        userFolderMovieRepository.save(mappingUtils.mapRToMovieEntity(folderRequestDto));
    }

    @Override
    public void deleteFolderPersons(FolderPersonResponseDto folderPersonResponseDto) {
        userFolderPersonRepository.delete(mappingUtils.mapToPersonEntity(folderPersonResponseDto));
    }

    @Override
    public void deleteFolderMovies(FolderMovieResponseDto folderMovieResponseDto) {
        userFolderMovieRepository.delete(mappingUtils.mapToMovieEntity(folderMovieResponseDto));
    }
}
