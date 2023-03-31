package com.kata.cinema.base.webapp.facade.collection.impl;

import com.kata.cinema.base.converter.collection.CollectionMapper;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.service.dto.CollectionDtoService;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.webapp.facade.collection.CollectionServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CollectionServiceFacadeImpl implements CollectionServiceFacade {

    private final CollectionService collectionService;

    private final CollectionDtoService dtoService;

    private final CollectionMapper collectionMapper;


    @Override
    public void save(CollectionRequestDto requestDto) {
        collectionService.save(collectionMapper.toEntity(requestDto));
    }

    @Override
    public void deleteById(Long id) {
        collectionService.deleteById(id);
    }

    @Override
    public void update(CollectionRequestDto requestDto, Long id) {
        Collection collection = collectionMapper.toEntity(requestDto);
        if (collectionService.getById(id) == null) {
            collectionService.save(collection);
        }
        collection.setId(id);
        collectionService.save(collection);
    }

    @Override
    public Collection getById(Long id) {
        return collectionService.getById(id);
    }

    @Override
    public List<CollectionResponseDto> getAllCollections() {
        return dtoService.getAllCollections();
    }

    @Override
    public List<CollectionResponseDto> getCollectionsByCategoryId(Long categoryId) {
        return dtoService.getCollectionsByCategoryId(categoryId);
    }
}
