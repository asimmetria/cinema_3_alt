package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.FolderPersonPositional;

public interface FolderPersonPositionalService {

    void save(Long folderId, Long personId);

    void update(Long folderId, Long personId, Integer newPosition);

    void deleteById(Long id);

    FolderPersonPositional getProxyById(Long id);

}
