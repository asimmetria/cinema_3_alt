package com.kata.cinema.base.webapp.facade.person;

import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;


public interface UserFolderPersonPositionalServiceFacade {

    FolderPersonPositional getByFolderIdAndPersonId(Long folderId, Long personId);

    void createFolderPersonPositional(Long folderId, Long personId);

    void deleteFolderPersonPositionalById(Long id);

    void updateFolderPersonPositional(Long folderId, Long personId, Integer newPosition);
}
