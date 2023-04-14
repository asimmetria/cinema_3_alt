package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.FolderMoviePositionalRepository;
import com.kata.cinema.base.validation.FolderMoviePositionalValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FolderMoviePositionalValidationImpl implements FolderMoviePositionalValidation {

    private final FolderMoviePositionalRepository folderMoviePositionalRepository;

    @Override
    public void isExistFolderMoviePositionalById(Long id) {
        log.debug("Start validation is exist FolderMoviePositional with id = {}", id);

        if (!folderMoviePositionalRepository.existsById(id)) {
            log.error("FolderMoviePositional with id = {} does not exist", id);
            throw new NotFoundEntityException(String.format("FolderMoviePositional с таким id = %s не существует", id));
        }

        log.info("Success validation is exist FolderMoviePositional with id = {}", id);
    }
}
