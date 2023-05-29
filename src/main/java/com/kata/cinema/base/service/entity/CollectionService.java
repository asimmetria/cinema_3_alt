package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.entitys.Collection;

import java.util.List;

public interface CollectionService {

    void save(Collection collection);

    void deleteById(Long id);

    Collection getById(Long id);

    void deactivateCollection(Long id);

    void activateCollection(Long id);

    void addMovieToCollection(Collection collection, List<Long> movieIds)
        throws CollectionNotFoundException, MovieNotFoundException;

    void deleteMovieFromCollection(Collection collection, List<Long> movieIds)
        throws CollectionNotFoundException, MovieNotFoundException;


}
