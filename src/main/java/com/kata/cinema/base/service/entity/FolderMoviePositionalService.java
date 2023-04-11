package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;

public interface FolderMoviePositionalService {

    void save(Long folderId, Long movieId);

    void update(Long folderId, Long movieId, Integer newPosition);

    void deleteById(Long id);

    FolderMoviePositional getProxyById(Long id);

}
