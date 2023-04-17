package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.validation.FolderValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FolderValidationImpl implements FolderValidation {

    private final FolderRepository folderRepository;

    @Override
    public void isExistFolderById(long id) {
        log.debug("Start validation is exist folder with id = {}", id);

        if (!folderRepository.existsById(id)) {
            log.error("Folder with id = {} is not exist", id);
            throw new NotFoundEntityException(String.format("Папка с таким id = %s не существует", id));
        }

        log.info("Success validation is exist folder with id = {}", id);
    }
}