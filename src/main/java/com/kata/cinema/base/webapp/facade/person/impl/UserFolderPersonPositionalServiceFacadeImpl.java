package com.kata.cinema.base.webapp.facade.person.impl;

import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import com.kata.cinema.base.validation.FolderPersonPositionalValidation;
import com.kata.cinema.base.validation.FolderValidation;
import com.kata.cinema.base.validation.PersonValidation;
import com.kata.cinema.base.webapp.facade.person.UserFolderPersonPositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFolderPersonPositionalServiceFacadeImpl implements UserFolderPersonPositionalServiceFacade {

    private final FolderPersonPositionalService folderPersonPositionalService;
    private final FolderPersonPositionalValidation folderPersonPositionalValidation;
    private final FolderValidation folderValidation;
    private final PersonValidation personValidation;

    @Override
    public FolderPersonPositional getByFolderIdAndPersonId(Long folderId, Long personId) {
        folderValidation.isExistFolderById(folderId);
        personValidation.isExistPersonById(personId);
        return folderPersonPositionalService.getByFolderIdAndPersonId(folderId, personId);
    }

    @Override
    public void createFolderPersonPositional(Long folderId, Long personId) {
        folderValidation.isExistFolderById(folderId);
        personValidation.isExistPersonById(personId);
        folderPersonPositionalService.save(folderId, personId);
    }

    @Override
    public void deleteFolderPersonPositionalById(Long id) {
        folderPersonPositionalValidation.isExistFolderPersonPositionalById(id);
        folderPersonPositionalService.deleteById(id);
    }

    @Override
    public void updateFolderPersonPositional(Long folderId, Long personId, Integer newPosition) {
        folderValidation.isExistFolderById(folderId);
        personValidation.isExistPersonById(personId);
        folderPersonPositionalService.update(folderId, personId, newPosition);
    }
}
