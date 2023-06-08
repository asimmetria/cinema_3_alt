package com.kata.cinema.base.webapp.facade.unauthorized;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.entitys.Person;

import java.util.List;

public interface PersonServiceFacade {
    PersonViewResponseDto getPersonViewById(Long id);
}
