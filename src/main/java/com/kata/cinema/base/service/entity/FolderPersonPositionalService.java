package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.FolderPersonPositional;

public interface FolderPersonPositionalService {

    void save(FolderPersonPositional folderPersonPositional);

    void update(FolderPersonPositional folderPersonPositional);

    void deleteById(Long id);

    FolderPersonPositional getProxyById(Long id);
}
