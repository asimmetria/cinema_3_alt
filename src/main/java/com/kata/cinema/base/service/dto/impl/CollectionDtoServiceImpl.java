package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.repository.CollectionRepository;
import com.kata.cinema.base.service.dto.CollectionDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionDtoServiceImpl implements CollectionDtoService {

    private final CollectionRepository collectionRepository;

    @Override
    public List<CollectionResponseDto> getAllCollections() {
        return collectionRepository.getAllCollections();
    }

    @Override
    public List<CollectionResponseDto> getCollectionsByCategoryId(Long categoryId) {
        return collectionRepository.getCollectionsByCategoryId(categoryId);
    }
}
