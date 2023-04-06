package com.kata.cinema.base.webapp.facade.person.impl;


import com.kata.cinema.base.converter.person.FolderPersonPositionalMapper;
import com.kata.cinema.base.models.dto.request.FolderPersonPositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.service.dto.FolderPersonPositionalDtoService;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import com.kata.cinema.base.webapp.facade.person.UserFolderPersonPositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFolderPersonPositionalServiceFacadeImpl implements UserFolderPersonPositionalServiceFacade {

    private final FolderPersonPositionalService folderPersonPositionalService;
    private final FolderPersonPositionalMapper folderPersonPositionalMapper;
    private final FolderPersonPositionalDtoService folderPersonPositionalDtoService;


    @Override
    public FolderPersonPositionalResponseDto getFolderPersonPositionalByFolderAndPerson(Long folderId, Long personId) {
        return folderPersonPositionalDtoService.getFolderPersonPositionalByFolderAndPerson(folderId, personId);
    }

    @Override
    public void addPersonToFolder(FolderPersonPositionalRequestDto personPositionalRequestDto) {
        folderPersonPositionalService.save(folderPersonPositionalMapper.toEntity(personPositionalRequestDto));

    }

    @Override
    public void deleteFolderPersonPositionalById(Long id) {
        folderPersonPositionalService.deleteById(id);
    }

    @Override
    public List<FolderPersonPositionalResponseDto> getAll() {
        return folderPersonPositionalDtoService.getAll();
    }

    @Override
    public void updatePerson(FolderPersonPositionalRequestDto personPositionalRequestDto) {
        folderPersonPositionalService.update(folderPersonPositionalMapper.toEntity(personPositionalRequestDto));
    }
}
