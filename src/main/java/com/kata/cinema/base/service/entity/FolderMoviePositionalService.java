package com.kata.cinema.base.service.entity;


public interface FolderMoviePositionalService {

    void save(Long folderId, Long movieId);

    void update(Long folderId, Long movieId, Integer newPosition);

    void deleteById(Long id);

}
