package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Collection;

import java.util.List;

public interface CollectionService {

    void save(Collection collection);

    void deleteById(Long id);

    Collection getById(Long id);

}
