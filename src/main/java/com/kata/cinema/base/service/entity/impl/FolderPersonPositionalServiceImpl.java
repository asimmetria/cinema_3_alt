package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderPerson;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.repository.FolderPersonPositionalRepository;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FolderPersonPositionalServiceImpl implements FolderPersonPositionalService {

    private final FolderPersonPositionalRepository folderPersonPositionalRepository;
    private final FolderRepository folderRepository;
    private final PersonRepository personRepository;


    @Override
    public void save(Long folderId, Long personId) {
        try {

            FolderPersonPositional folderPersonPositional = new FolderPersonPositional();
            folderPersonPositional.setFolder((FolderPerson) folderRepository.findById(folderId).orElseThrow(EntityNotFoundException::new));
            folderPersonPositional.setPerson(personRepository.findById(personId).orElseThrow(EntityNotFoundException::new));
            folderPersonPositional.setPositional(folderPersonPositionalRepository.getLastPersonPosition() + 1);
            folderPersonPositionalRepository.save(folderPersonPositional);
        } catch (NotFoundEntityException e) {
            throw new RuntimeException("Failed to save FolderPersonPositional. Folder with id " + folderId + " not found.", e);
        }
    }

    @Override
    public void update(Long folderId, Long personId, Integer newPosition) {
        FolderPersonPositionalResponseDto folderPersonPositionalResponse =
                folderPersonPositionalRepository.getByFolderIdAndPersonId(folderId, personId);
        Optional<FolderPersonPositional> folderPersonPositionalOptional =
                folderPersonPositionalRepository.findById(folderPersonPositionalResponse.getId());

        if (folderPersonPositionalOptional.isPresent()) {
            folderPersonPositionalRepository.findAll()
                    .stream()
                    .filter(x -> x.getPositional() >= newPosition)
                    .forEach(x -> {
                        Optional<FolderPersonPositional> fppOptional = folderPersonPositionalRepository.findById(x.getId());
                        if (fppOptional.isPresent()) {
                            FolderPersonPositional fpp = fppOptional.get();
                            fpp.setPositional(x.getPositional() + 1);
                            folderPersonPositionalRepository.save(fpp);
                        }
                    });

            FolderPersonPositional folderPersonPositional = folderPersonPositionalOptional.get();
            folderPersonPositional.setPositional(newPosition);
            folderPersonPositionalRepository.save(folderPersonPositional);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<FolderPersonPositional> folderPersonPositionalOptional = folderPersonPositionalRepository.findById(id);
        if (folderPersonPositionalOptional.isPresent()) {
            folderPersonPositionalRepository.deleteById(id);
            FolderPersonPositional folderPersonPositional = folderPersonPositionalOptional.get();
            folderPersonPositionalRepository.findAll().stream()
                    .filter(x -> x.getPositional() > folderPersonPositional.getPositional())
                    .forEach(x -> {
                        Optional<FolderPersonPositional> fmpOptional = folderPersonPositionalRepository.findById(x.getId());
                        if (fmpOptional.isPresent()) {
                            FolderPersonPositional fpp = fmpOptional.get();
                            fpp.setPositional(x.getPositional() - 1);
                            folderPersonPositionalRepository.save(fpp);
                        }
                    });
        }
    }

}
