package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.repository.FolderPersonPositionalRepository;
import com.kata.cinema.base.service.dto.FolderPersonPositionalDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderPersonPositionalDtoServiceImpl implements FolderPersonPositionalDtoService {

    private final FolderPersonPositionalRepository folderPersonPositionalRepository;

    @Override
    public FolderPersonPositionalResponseDto getFolderPersonPositionalByFolderAndPerson(Long folderId, Long personId) {
        return folderPersonPositionalRepository.getFolderPersonPositionalByFolderAndPerson(folderId, personId);
    }

    @Override
    public List<FolderPersonPositionalResponseDto> getAll() {
        return folderPersonPositionalRepository.getAll();
    }
}
