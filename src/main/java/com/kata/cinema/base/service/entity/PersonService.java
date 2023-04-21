package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAllPersons();

    Person getPerson(Long id);

    void save(Person person);

    void deleteById(Long id);
}
