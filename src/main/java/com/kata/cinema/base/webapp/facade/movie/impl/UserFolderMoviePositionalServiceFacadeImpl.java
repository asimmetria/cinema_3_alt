package com.kata.cinema.base.webapp.facade.movie.impl;

import com.kata.cinema.base.converter.movie.FolderMoviePositionalMapper;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.service.dto.FolderMoviePositionalDtoService;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import com.kata.cinema.base.webapp.facade.movie.UserFolderMoviePositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFolderMoviePositionalServiceFacadeImpl implements UserFolderMoviePositionalServiceFacade {

    private final FolderMoviePositionalService folderMoviePositionalService;
    private final FolderMoviePositionalDtoService folderMoviePositionalDtoService;
    private final FolderMoviePositionalMapper folderMoviePositionalMapper;

    @Override
    public FolderMoviePositional getByFolderIdAndMovieId(Long folderId, Long movieId) {
        return folderMoviePositionalMapper.toEntity(folderMoviePositionalDtoService.getByFolderIdAndMovieId(folderId, movieId));
    }

    @Override
    public void createFolderMoviePositional(Long folderId, Long movieId) {
        folderMoviePositionalService.save(folderId, movieId);
    }

    @Override
    public void deleteFolderMoviePositionalById(Long id) {
        folderMoviePositionalService.deleteById(id);
    }

    @Override
    public void updateFolderMoviePositional(Long folderId, Long movieId, Integer newPosition) {
        folderMoviePositionalService.update(folderId, movieId, newPosition);
    }
}