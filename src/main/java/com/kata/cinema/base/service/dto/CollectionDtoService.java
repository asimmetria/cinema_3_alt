package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.CollectionResponseDto;

import java.util.List;

public interface CollectionDtoService {

    List<CollectionResponseDto> getAllCollections(Long userId);

    List<CollectionResponseDto> getCollectionsByCategoryId(Long categoryId, Long userId);
}
