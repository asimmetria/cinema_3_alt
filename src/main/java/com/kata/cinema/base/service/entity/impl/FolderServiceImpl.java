package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.service.entity.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public void save(Folder folder) {
        folderRepository.save(folder);
    }

    @Override
    public void update(Folder folder) {
        folderRepository.save(folder);
    }

    @Override
    public void deleteById(long id) {
        folderRepository.deleteById(id);
    }

    @Override
    public Folder getProxyById(long id) {
        return folderRepository.getReferenceById(id);
    }

    @Override
    public boolean existsById(long id) {
        return folderRepository.existsById(id);
    }
}
