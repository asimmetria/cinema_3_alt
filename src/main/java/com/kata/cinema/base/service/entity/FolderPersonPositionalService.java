package com.kata.cinema.base.service.entity;


public interface FolderPersonPositionalService {

    void save(Long folderId, Long personId);

    void update(Long folderId, Long personId, Integer newPosition);

    void deleteById(Long id);

}
