package com.kata.cinema.base.converter.movie;

import com.kata.cinema.base.converter.DtoMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.response.MoviePersonResponseDto;
import com.kata.cinema.base.models.entitys.Person;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(config = ConfigMapper.class)
public interface MoviePersonMapper extends DtoMapper<List<MoviePersonResponseDto>, Person> {

    @Override
    default List<MoviePersonResponseDto> toDto(Person person) {
        List<Long> professionIds = person.getCasts().stream().map(e -> e.getProfession().getId()).toList();
        List<MoviePersonResponseDto> personsDto = new ArrayList<>();

        for (Long professionId : professionIds) {
            MoviePersonResponseDto personDto = new MoviePersonResponseDto();
            personDto.setProfessionId(professionId);
            personDto.setPersonId(person.getId());
            personDto.setFullName(person.getFullName());
            personDto.setOriginalFullName(person.getOriginalFullName());
            personsDto.add(personDto);
        }

        return personsDto;
    }
}
