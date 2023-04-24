package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.repository.CollectionRepository;
import com.kata.cinema.base.service.entity.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }

    @Override
    public void deleteById(Long id) {
        collectionRepository.deleteById(id);
    }

    @Override
    public Collection getById(Long id) {
        return collectionRepository.getCollectionById(id);
    }


}
