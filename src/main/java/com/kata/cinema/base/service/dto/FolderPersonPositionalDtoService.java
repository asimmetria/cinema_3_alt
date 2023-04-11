package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;

public interface FolderPersonPositionalDtoService {

    FolderPersonPositionalResponseDto getByFolderIdAndPersonId(Long folderId, Long personId);

}
