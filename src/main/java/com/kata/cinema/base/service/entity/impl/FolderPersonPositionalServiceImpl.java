package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.repository.FolderPersonPositionalRepository;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FolderPersonPositionalServiceImpl implements FolderPersonPositionalService {

    private final FolderPersonPositionalRepository folderPersonPositionalRepository;

    @Override
    public void save(FolderPersonPositional folderPersonPositional) {
        folderPersonPositionalRepository.save(folderPersonPositional);
    }

    @Override
    public void update(FolderPersonPositional folderPersonPositional) {
        folderPersonPositionalRepository.save(folderPersonPositional);
    }

    @Override
    public void deleteById(Long id) {
        folderPersonPositionalRepository.deleteById(id);
    }

    @Override
    public FolderPersonPositional getProxyById(Long id) {
        return folderPersonPositionalRepository.getReferenceById(id);
    }
}
