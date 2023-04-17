package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Folder;

public interface FolderService {
    void save(Folder folder);

    void update(Folder folder);

    void deleteById(long id);

    Folder getProxyById(long id);

    boolean existsById(long id);

}
