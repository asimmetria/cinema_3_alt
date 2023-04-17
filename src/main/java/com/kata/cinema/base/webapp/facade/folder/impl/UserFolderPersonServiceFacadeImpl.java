package com.kata.cinema.base.webapp.facade.folder.impl;

import com.kata.cinema.base.converter.folder.FolderPersonMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.entitys.FolderPerson;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.service.dto.FolderDtoService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.service.entity.UserService;
import com.kata.cinema.base.validation.FolderValidation;
import com.kata.cinema.base.webapp.facade.folder.UserFolderPersonServiceFacade;
import com.kata.cinema.base.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFolderPersonServiceFacadeImpl implements UserFolderPersonServiceFacade {

    private final FolderService foldersServices;
    private final UserService userService;
    private final FolderPersonMapper folderPersonMapper;
    private final FolderDtoService folderDtoService;
    private final UserValidation userValidation;
    private final FolderValidation folderValidation;



    @Override
    public List<FolderPersonResponseDto> getFolderPersonsByUserId(long userId) {
        userValidation.isExistUserById(userId);
        return folderDtoService.getPersonFoldersByUserId(userId);
    }



    @Override
    public void createFolderPerson(Long userId, FolderRequestDto newFolderPerson) {
        userValidation.isExistUserById(userId);
        FolderPerson folderPerson = folderPersonMapper.toEntity(newFolderPerson);
        folderPerson.setUser(userService.getById(userId));
        foldersServices.save(folderPerson);
    }


    @Override
    public void deleteFolderById(long id) {
        folderValidation.isExistFolderById(id);
        foldersServices.deleteById(id);
    }
}

