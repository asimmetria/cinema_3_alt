package com.kata.cinema.base.webapp.facade.unauthorized.impl;

import com.kata.cinema.base.converter.collection.CollectionMapper;
import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionMoviesResponseDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.service.dto.CollectionDtoService;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.validation.UserValidation;
import com.kata.cinema.base.webapp.facade.unauthorized.CollectionServiceFacade;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CollectionServiceFacadeImpl implements CollectionServiceFacade {

    private final CollectionService collectionService;

    private final CollectionDtoService dtoService;

    private final CollectionMapper collectionMapper;

    private final UserValidation userValidation;


    @Override
    public void save(CollectionRequestDto requestDto) {
        collectionService.save(collectionMapper.toEntity(requestDto));
    }

    @Override
    public void deleteById(Long id) {
        collectionService.deleteById(id);
    }

    @Override
    @Transactional
    @Access(value = AccessType.FIELD)
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
    public List<CollectionResponseDto> getAllCollections(Long userId) {
        userValidation.isExistUserById(userId);
        return dtoService.getAllCollections(userId);
    }

    @Override
    public List<CollectionResponseDto> getCollectionsByCategoryId(Long categoryId, Long userId) {
        userValidation.isExistUserById(userId);
        return dtoService.getCollectionsByCategoryId(categoryId, userId);
    }

    @Override
    public void deactivateById(Long id) {
        collectionService.deactivateCollection(id);
    }

    @Override
    public void activateById(Long id) {
        collectionService.activateCollection(id);
    }

    @Override
    @Transactional
    public void addMoviesToCollection(Long collectionId, List<Long> movieIds) throws CollectionNotFoundException, MovieNotFoundException {
        Collection collection = collectionService.getById(collectionId);
        if (collection == null) {
            throw new CollectionNotFoundException("Collection not found");
        }
        collectionService.addMovieToCollection(collection, movieIds);
    }

    @Override
    @Transactional
    public void deleteMoviesFromCollection(Long collectionId, List<Long> movieIds) throws CollectionNotFoundException, MovieNotFoundException {
        Collection collection = collectionService.getById(collectionId);
        if (collection == null) {
            throw new CollectionNotFoundException("Collection not found");
        }
        collectionService.deleteMovieFromCollection(collection, movieIds);
    }

    @Override
    public CollectionMoviesResponseDto getCollectionMovie(Long id, Long countryId, Long genreId, LocalDate date, CollectionSortType collectionSortType, int pageNumber, Long size) {
        return collectionService.getCollectionMovie(id, countryId, genreId, date, collectionSortType, pageNumber, size);
    }
}
