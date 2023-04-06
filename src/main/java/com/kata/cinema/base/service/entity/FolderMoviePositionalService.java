package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;

public interface FolderMoviePositionalService {

    void save(FolderMoviePositional folderMoviePositional);

    void update(FolderMoviePositional folderMoviePositional);

    void deleteById(Long id);

    FolderMoviePositional getProxyById(Long id);

}
