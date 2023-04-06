package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;

import java.util.List;

public interface FolderPersonPositionalDtoService {

    FolderPersonPositionalResponseDto getFolderPersonPositionalByFolderAndPerson(Long folderId, Long personId);

    List<FolderPersonPositionalResponseDto> getAll();
}
