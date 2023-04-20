package com.kata.cinema.base.webapp.facade.folder;

import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;

import java.util.List;

public interface UserFolderPersonServiceFacade {

    List<FolderPersonResponseDto> getFolderPersonsByUserId(long userId);

    void createFolderPerson(Long userId, FolderRequestDto newFolderPerson);

    void deleteFolderById(long id);
}
