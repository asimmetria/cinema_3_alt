package com.kata.cinema.base.webapp.facade.user.impl;

import com.kata.cinema.base.converter.folder.FolderMovieMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.service.dto.FolderDtoService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.service.entity.UserService;
import com.kata.cinema.base.validation.FolderValidation;
import com.kata.cinema.base.validation.UserValidation;
import com.kata.cinema.base.webapp.facade.user.UserFolderMovieServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFolderMovieServiceFacadeImpl implements UserFolderMovieServiceFacade {

    private final FolderService foldersServices;
    private final FolderMovieMapper folderMovieMapper;
    private final FolderDtoService folderDtoService;
    private final UserValidation userValidation;
    private final FolderValidation folderValidation;
    private final UserService userService;

    @Override
    public List<FolderMovieResponseDto> getFolderMoviesByUserId(long userId) {
        userValidation.isExistUserById(userId);
        return folderDtoService.getMovieFoldersByUserId(userId);
    }

    @Override
    public void createFolderMovies(FolderRequestDto folderRequestDto, Long userId) {
        userValidation.isExistUserById(userId);
        FolderMovie folder = folderMovieMapper.toEntity(folderRequestDto);
        folder.setUser(userService.getById(userId));
        foldersServices.save(folder);
    }

    @Override
    public void deleteFolderById(long id) {
        folderValidation.isExistFolderById(id);
        foldersServices.deleteById(id);
    }
}
