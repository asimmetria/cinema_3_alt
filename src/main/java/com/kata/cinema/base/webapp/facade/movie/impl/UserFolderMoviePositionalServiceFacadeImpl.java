package com.kata.cinema.base.webapp.facade.movie.impl;

import com.kata.cinema.base.converter.movie.FolderMoviePositionalMapper;
import com.kata.cinema.base.models.dto.request.FolderMoviePositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.service.dto.FolderMoviePositionalDtoService;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import com.kata.cinema.base.webapp.facade.movie.UserFolderMoviePositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFolderMoviePositionalServiceFacadeImpl implements UserFolderMoviePositionalServiceFacade {

    private final FolderMoviePositionalService folderMoviePositionalService;
    private final FolderMoviePositionalMapper folderMoviePositionalMapper;
    private final FolderMoviePositionalDtoService folderMoviePositionalDtoService;


    @Override
    public FolderMoviePositionalResponseDto getFolderMoviePositionalByFolderAndMovie(Long folderId, Long movieId) {
        return folderMoviePositionalDtoService.getFolderMoviePositionalByFolderAndMovie(folderId, movieId);
    }

    @Override
    public void addMovieToFolder(FolderMoviePositionalRequestDto moviePositionalRequestDto) {
        folderMoviePositionalService.save(folderMoviePositionalMapper.toEntity(moviePositionalRequestDto));
    }

    @Override
    public void deleteFolderMoviePositionalById(Long id) {
        folderMoviePositionalService.deleteById(id);
    }

    @Override
    public List<FolderMoviePositionalResponseDto> getAll() {
        return folderMoviePositionalDtoService.getAll();
    }

    @Override
    public void updateMovie(FolderMoviePositionalRequestDto moviePositionalRequestDto) {
        folderMoviePositionalService.update(folderMoviePositionalMapper.toEntity(moviePositionalRequestDto));
    }


}