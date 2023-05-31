package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.dto.response.PersonInfoDto;
import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.dto.PersonDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonDtoServiceImpl implements PersonDtoService {
    private final PersonRepository personRepository;

    @Override
    public PersonViewResponseDto getPersonViewById(Long personId) {
        PersonInfoDto personInfo = personRepository.getPersonInfo(personId);
        long movieCount = personRepository.getMovieCountForPerson(personId);
        List<GenreResponseDto> topGenres = personRepository.getTopGenresForPerson(personId);
        List<ProfessionResponseDto> professions = personRepository.getProfessionsForPerson(personId);
        return new PersonViewResponseDto(
                personInfo.getId(),
                personInfo.getPreviewUrl(),
                personInfo.getFullName(),
                personInfo.getOriginalFullName(),
                personInfo.getHeight(),
                personInfo.getDateBirth(),
                personInfo.getPlaceOfBirth(),
                movieCount,
                topGenres,
                professions);
    }
}
