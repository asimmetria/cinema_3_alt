package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getPersonById(Long id);
}
