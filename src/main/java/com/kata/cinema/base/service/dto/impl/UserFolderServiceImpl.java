package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.FolderPersonResponseDto;
import com.kata.cinema.base.repository.UserFolderMovieRepository;
import com.kata.cinema.base.repository.UserFolderPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFolderServiceImpl implements UserFolderService{

    private final UserFolderMovieRepository userFolderMovieRepository;
    private final UserFolderPersonRepository userFolderPersonRepository;

    public UserFolderServiceImpl(UserFolderMovieRepository userFolderMovieRepository, UserFolderPersonRepository userFolderPersonRepository) {
        this.userFolderMovieRepository = userFolderMovieRepository;
        this.userFolderPersonRepository = userFolderPersonRepository;
    }

    @Override
    public List<FolderMovieResponseDto> getFolderMovies() {
        return userFolderMovieRepository.findAll();
    }

    @Override
    public List<FolderPersonResponseDto> getFolderPersons() {
        return userFolderPersonRepository.findAll();
    }

    @Override
    public void createFolderPersons(FolderPersonResponseDto folderPersonResponseDto) {
        userFolderPersonRepository.save(folderPersonResponseDto);
    }

    @Override
    public void createFolderMovies(FolderMovieResponseDto folderMovieResponseDto) {
        userFolderMovieRepository.save(folderMovieResponseDto);
    }

    @Override
    public void deleteFolderPersons(FolderPersonResponseDto folderPersonResponseDto) {
        userFolderPersonRepository.delete(folderPersonResponseDto);
    }

    @Override
    public void deleteFolderMovies(FolderMovieResponseDto folderMovieResponseDto) {
        userFolderMovieRepository.delete(folderMovieResponseDto);
    }
}
