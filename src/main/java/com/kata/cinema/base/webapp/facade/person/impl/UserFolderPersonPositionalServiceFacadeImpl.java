package com.kata.cinema.base.webapp.facade.person.impl;

import com.kata.cinema.base.converter.person.FolderPersonPositionalMapper;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.service.dto.FolderPersonPositionalDtoService;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import com.kata.cinema.base.webapp.facade.person.UserFolderPersonPositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFolderPersonPositionalServiceFacadeImpl implements UserFolderPersonPositionalServiceFacade {

    private final FolderPersonPositionalService folderPersonPositionalService;
    private final FolderPersonPositionalDtoService folderPersonPositionalDtoService;
    private final FolderPersonPositionalMapper folderPersonPositionalMapper;

    @Override
    public FolderPersonPositional getByFolderIdAndPersonId(Long folderId, Long personId) {
        return folderPersonPositionalMapper.toEntity(folderPersonPositionalDtoService.getByFolderIdAndPersonId(folderId, personId));
    }

    @Override
    public void createFolderPersonPositional(Long folderId, Long personId) {
        folderPersonPositionalService.save(folderId, personId);
    }

    @Override
    public void deleteFolderPersonPositionalById(Long id) {
        folderPersonPositionalService.deleteById(id);
    }

    @Override
    public void updateFolderPersonPositional(Long folderId, Long personId, Integer newPosition) {
        folderPersonPositionalService.update(folderId, personId, newPosition);
    }
}
