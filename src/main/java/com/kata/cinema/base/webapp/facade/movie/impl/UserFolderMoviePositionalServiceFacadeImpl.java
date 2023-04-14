package com.kata.cinema.base.webapp.facade.movie.impl;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import com.kata.cinema.base.validation.FolderMoviePositionalValidation;
import com.kata.cinema.base.validation.FolderValidation;
import com.kata.cinema.base.validation.MovieValidation;
import com.kata.cinema.base.webapp.facade.movie.UserFolderMoviePositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFolderMoviePositionalServiceFacadeImpl implements UserFolderMoviePositionalServiceFacade {

    private final FolderMoviePositionalService folderMoviePositionalService;
    private final FolderMoviePositionalValidation folderMoviePositionalValidation;
    private final FolderValidation folderValidation;
    private final MovieValidation movieValidation;

    @Override
    public FolderMoviePositional getByFolderIdAndMovieId(Long folderId, Long movieId) {
        folderValidation.isExistFolderById(folderId);
        movieValidation.isExistMovieById(movieId);
        return folderMoviePositionalService.getByFolderIdAndMovieId(folderId, movieId);
    }

    @Override
    public void createFolderMoviePositional(Long folderId, Long movieId) {
        folderValidation.isExistFolderById(folderId);
        movieValidation.isExistMovieById(movieId);
        folderMoviePositionalService.save(folderId, movieId);
    }

    @Override
    public void deleteFolderMoviePositionalById(Long id) {
        folderMoviePositionalValidation.isExistFolderMoviePositionalById(id);
        folderMoviePositionalService.deleteById(id);
    }

    @Override
    public void updateFolderMoviePositional(Long folderId, Long movieId, Integer newPosition) {
        folderValidation.isExistFolderById(folderId);
        movieValidation.isExistMovieById(movieId);
        folderMoviePositionalService.update(folderId, movieId, newPosition);
    }
}