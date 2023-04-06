package com.kata.cinema.base.webapp.facade.person;

import com.kata.cinema.base.models.dto.request.FolderPersonPositionalRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;

import java.util.List;

public interface UserFolderPersonPositionalServiceFacade {

    FolderPersonPositionalResponseDto getFolderPersonPositionalByFolderAndPerson(Long folderId, Long personId);

    void addPersonToFolder(FolderPersonPositionalRequestDto personRequestDto);

    void deleteFolderPersonPositionalById(Long id);

    List<FolderPersonPositionalResponseDto> getAll();

    void updatePerson(FolderPersonPositionalRequestDto personRequestDto);
}
