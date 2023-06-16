package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;

public interface PersonDtoService {
    PersonViewResponseDto getPersonViewById(Long personId);
}
