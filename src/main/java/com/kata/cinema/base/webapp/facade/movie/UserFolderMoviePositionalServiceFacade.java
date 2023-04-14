package com.kata.cinema.base.webapp.facade.movie;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;

public interface UserFolderMoviePositionalServiceFacade {

    FolderMoviePositional getByFolderIdAndMovieId(Long folderId, Long movieId);

    void createFolderMoviePositional(Long folderId, Long movieId);

    void deleteFolderMoviePositionalById(Long id);

    void updateFolderMoviePositional(Long folderId, Long movieId, Integer newPosition);
}
