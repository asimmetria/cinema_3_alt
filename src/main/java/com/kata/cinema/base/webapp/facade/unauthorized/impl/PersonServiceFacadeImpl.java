package com.kata.cinema.base.webapp.facade.unauthorized.impl;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.entitys.Person;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.dto.PersonDtoService;
import com.kata.cinema.base.service.entity.PersonService;
import com.kata.cinema.base.webapp.facade.unauthorized.PersonServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PersonServiceFacadeImpl implements PersonServiceFacade {
    private final PersonDtoService personDtoService;

    @Override
    public PersonViewResponseDto getPersonViewById(Long id) {
        return personDtoService.getPersonViewById(id);
    }

}
