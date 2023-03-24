package com.kata.cinema.base.webapp.facade.folder.impl;

import com.kata.cinema.base.converter.folder.FolderPersonMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.service.dto.FolderDtoService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.webapp.facade.folder.UserFolderPersonServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFolderPersonServiceFacadeImpl implements UserFolderPersonServiceFacade {

    private final FolderService foldersServices;
    private final FolderPersonMapper folderPersonMapper;
    private final FolderDtoService folderDtoService;

    @Override
    public List<FolderPersonResponseDto> getFolderPersonsByUserId(long userId) {
        //TODO валидация
        return folderDtoService.getPersonFoldersByUserId(userId);
    }

    @Override
    public void createFolderPerson(FolderRequestDto newFolderPerson) {
        foldersServices.save(folderPersonMapper.toEntity(newFolderPerson));
    }

    @Override
    public void deleteFolderById(long id) {
        //TODO валидация
        foldersServices.deleteById(id);
    }
}
