package com.kata.cinema.base.webapp.facade.folder.impl;

import com.kata.cinema.base.converter.folder.FolderMovieMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.service.dto.FolderDtoService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.webapp.facade.folder.UserFolderMovieServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFolderMovieServiceFacadeImpl implements UserFolderMovieServiceFacade {

    private final FolderService foldersServices;
    private final FolderMovieMapper folderMovieMapper;
    private final FolderDtoService folderDtoService;

    @Override
    public List<FolderMovieResponseDto> getFolderMoviesByUserId(long userId) {
        //TODO валидация на существование пользователя
        return folderDtoService.getMovieFoldersByUserId(userId);
    }

    @Override
    public void createFolderMovies(FolderRequestDto folderRequestDto) {
        foldersServices.save(folderMovieMapper.toEntity(folderRequestDto));
    }

    @Override
    public void deleteFolderById(long id) {
        //TODO валдиация
        foldersServices.deleteById(id);
    }
}
