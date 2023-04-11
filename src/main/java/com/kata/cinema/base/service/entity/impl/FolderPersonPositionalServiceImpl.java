package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.FolderPersonPositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderPerson;
import com.kata.cinema.base.models.entitys.FolderPersonPositional;
import com.kata.cinema.base.repository.FolderPersonPositionalRepository;
import com.kata.cinema.base.repository.FolderRepository;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.entity.FolderPersonPositionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FolderPersonPositionalServiceImpl implements FolderPersonPositionalService {

    private final FolderPersonPositionalRepository folderPersonPositionalRepository;
    private final FolderRepository folderRepository;
    private final PersonRepository personRepository;


    @Override
    public void save(Long folderId, Long personId) {
        FolderPersonPositional folderPersonPositional = new FolderPersonPositional();
        folderPersonPositional.setFolder((FolderPerson) folderRepository.getReferenceById(folderId));
        folderPersonPositional.setPerson(personRepository.getReferenceById(personId));
        folderPersonPositional.setPositional(folderPersonPositionalRepository.getLastPersonPosition() + 1);
        folderPersonPositionalRepository.save(folderPersonPositional);
    }

    @Override
    public void update(Long folderId, Long personId, Integer newPosition) {
        FolderPersonPositionalResponseDto folderPersonPositionalResponse = folderPersonPositionalRepository.getByFolderIdAndPersonId(folderId, personId);
        FolderPersonPositional folderPersonPositional = new FolderPersonPositional();
        folderPersonPositional.setPositional(newPosition);
        folderPersonPositional.setFolder((FolderPerson) folderPersonPositionalResponse.getFolder());
        folderPersonPositional.setPerson(folderPersonPositionalResponse.getPerson());

        folderPersonPositionalRepository.findAll()
                .stream()
                .filter(x -> x.getPositional() > newPosition)
                .map(x -> {
                    FolderPersonPositional fpp = new FolderPersonPositional();
                    fpp.setPositional(x.getPositional() + 1);
                    fpp.setPerson(x.getPerson());
                    fpp.setFolder(x.getFolder());
                    return fpp;
                }).forEach(folderPersonPositionalRepository::save);
        folderPersonPositionalRepository.save(folderPersonPositional);
    }

    @Override
    public void deleteById(Long id) {
        FolderPersonPositional folderPersonPositional = folderPersonPositionalRepository.getReferenceById(id);
        folderPersonPositionalRepository.deleteById(id);
        folderPersonPositionalRepository.findAll().stream()
                .filter(x -> x.getPositional() > folderPersonPositional.getPositional())
                .map(x -> {
                    FolderPersonPositional fpp = new FolderPersonPositional();
                    fpp.setPositional(x.getPositional() - 1);
                    fpp.setPerson(x.getPerson());
                    fpp.setFolder(x.getFolder());
                    return fpp;
                }).forEach(folderPersonPositionalRepository::save);
    }

    @Override
    public FolderPersonPositional getProxyById(Long id) {
        return folderPersonPositionalRepository.getReferenceById(id);
    }
}
