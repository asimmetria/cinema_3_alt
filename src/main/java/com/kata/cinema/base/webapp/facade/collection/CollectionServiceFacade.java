package com.kata.cinema.base.webapp.facade.collection;

import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;

import java.util.List;

public interface CollectionServiceFacade {

    void save(CollectionRequestDto requestDto);

    void deleteById(Long id);

    void update(CollectionRequestDto requestDto, Long id);

    Collection getById(Long id);

    List<CollectionResponseDto> getAllCollections();

    List<CollectionResponseDto> getCollectionsByCategoryId(Long categoryId);


}
