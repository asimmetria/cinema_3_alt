package com.kata.cinema.base.webapp.facade.unauthorized;

import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;

import java.util.List;

public interface CollectionServiceFacade {

    void save(CollectionRequestDto requestDto);

    void deleteById(Long id);

    void update(CollectionRequestDto requestDto, Long id);

    Collection getById(Long id);

    List<CollectionResponseDto> getAllCollections(Long userId);

    List<CollectionResponseDto> getCollectionsByCategoryId(Long categoryId, Long userId);

    void deactivateById(Long id);

    void activateById(Long id);

    void addMoviesToCollection(Long collectionId, List<Long> movieIds)
        throws CollectionNotFoundException, MovieNotFoundException;

    void deleteMoviesFromCollection(Long collectionId, List<Long> movieIds)
        throws CollectionNotFoundException, MovieNotFoundException;

}
