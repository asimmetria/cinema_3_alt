package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Person;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.entity.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPerson(Long id) {
        return personRepository.getPersonById(id);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
