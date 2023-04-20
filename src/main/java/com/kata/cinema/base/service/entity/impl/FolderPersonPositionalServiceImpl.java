package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.entitys.FolderPerson;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.repository.FolderPersonPositionalRepository;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class FolderPersonPositionalServiceImpl implements FolderPersonPositionalService {

    private final FolderPersonPositionalRepository folderPersonPositionalRepository;
    private final FolderRepository folderRepository;
    private final PersonRepository personRepository;


    @Override
    public void save(Long folderId, Long personId) {
        FolderPersonPositional folderPersonPositional = new FolderPersonPositional();
        folderPersonPositional.setFolder((FolderPerson) folderRepository.findById(folderId).orElseThrow(
                () -> new NotFoundEntityException("Папка не существует")));
        folderPersonPositional.setPerson(personRepository.findById(personId).orElseThrow(
                () -> new NotFoundEntityException("Персона не существует")));
        folderPersonPositional.setPositional(folderPersonPositionalRepository.getLastPersonPosition() + 1);
        folderPersonPositionalRepository.save(folderPersonPositional);
    }

    @Override
    public void update(Long folderId, Long personId, Integer newPosition) {
        FolderPersonPositional folderPersonPositional =
                folderPersonPositionalRepository.getByFolderIdAndPersonId(folderId, personId);
        Integer oldPosition = folderPersonPositional.getPositional();

        if (oldPosition > newPosition) {
            folderPersonPositionalRepository.getAllBetweenTwoPositionsOrderedPositionDecreased(oldPosition, newPosition)
                    .forEach(fpp -> {
                        fpp.setPositional(fpp.getPositional() + 1);
                        folderPersonPositionalRepository.save(fpp);
                    });
        } else {
            folderPersonPositionalRepository.getAllBetweenTwoPositionsOrderedPositionIncreased(oldPosition, newPosition)
                    .forEach(fpp -> {
                        fpp.setPositional(fpp.getPositional() - 1);
                        folderPersonPositionalRepository.save(fpp);
                    });
        }

        folderPersonPositional.setPositional(newPosition);
        folderPersonPositionalRepository.save(folderPersonPositional);
    }

    @Override
    public void deleteById(Long id) {
        Integer deletedRecordPosition =
                folderPersonPositionalRepository.findById(id).orElseThrow(
                        () -> new NotFoundEntityException("FolderPersonPositional не существует")).getPositional();
        folderPersonPositionalRepository.deleteById(id);
        List<FolderPersonPositional> nextRecords =
                folderPersonPositionalRepository.getAllNextByPositionOrdered(deletedRecordPosition);
        nextRecords.forEach(fpp -> {
            fpp.setPositional(fpp.getPositional() - 1);
            folderPersonPositionalRepository.save(fpp);
        });
    }

    @Override
    public FolderPersonPositional getByFolderIdAndPersonId(Long folderId, Long personId) {
        return folderPersonPositionalRepository.getByFolderIdAndPersonId(folderId, personId);
    }

}
