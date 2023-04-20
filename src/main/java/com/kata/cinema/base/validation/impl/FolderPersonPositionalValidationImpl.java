package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.FolderPersonPositionalRepository;
import com.kata.cinema.base.validation.FolderPersonPositionalValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FolderPersonPositionalValidationImpl implements FolderPersonPositionalValidation {

    private final FolderPersonPositionalRepository folderPersonPositionalRepository;

    @Override
    public void isExistFolderPersonPositionalById(Long id) {
        log.debug("Start validation is exist FolderPersonPositional with id = {}", id);

        if (!folderPersonPositionalRepository.existsById(id)) {
            log.error("FolderPersonPositional with id = {} does not exist", id);
            throw new NotFoundEntityException(String.format("FolderPersonPositional с таким id = %s не существует", id));
        }

        log.info("Success validation is exist FolderPersonPositional with id = {}", id);
    }
}
