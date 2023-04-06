package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.service.entity.FolderMoviePositionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FolderMoviePositionalServiceImpl implements FolderMoviePositionalService {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;

    @Override
    public void save(FolderMoviePositional folderMoviePositional) {
        folderMoviePositionalRepository.save(folderMoviePositional);
    }

    @Override
    public void update(FolderMoviePositional folderMoviePositional) {
        folderMoviePositionalRepository.save(folderMoviePositional);
    }

    @Override
    public void deleteById(Long id) {
        folderMoviePositionalRepository.deleteById(id);
    }

    @Override
    public FolderMoviePositional getProxyById(Long id) {
        return folderMoviePositionalRepository.getReferenceById(id);
    }




}
