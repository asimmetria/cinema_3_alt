package com.kata.cinema.base.webapp.facade.excertion.impl;

import com.kata.cinema.base.converter.excertion.ExcertionMapper;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.entitys.Excertion;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.Person;
import com.kata.cinema.base.service.entity.ExcertionService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.PersonService;
import com.kata.cinema.base.webapp.facade.excertion.ExcertionServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExcertionServiceFacadeImpl implements ExcertionServiceFacade {
    private final ExcertionService excertionService;
    private final MovieService movieService;
    private final PersonService personService;
    private final ExcertionMapper excertionMapper;

    @Override
    public Page<ExcertionResponseDto> getMovieExcertion(Long id, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Excertion> excertions = excertionService.getMovieExcertion(id, pageable);
        Page<Excertion> entityPage = new PageImpl<>(excertions, pageable, excertions.size());
        return entityPage.map(excertionMapper::toDto);
    }

    @Override
    public Page<ExcertionResponseDto> getPersonExcertion(Long id, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Excertion> excertions = excertionService.getPersonExcertion(id, pageable);
        Page<Excertion> entityPage = new PageImpl<>(excertions, pageable, excertions.size());
        return entityPage.map(excertionMapper::toDto);
    }

    @Override
    public void createMovieExcertion(Long id, ExcertionRequestDto excertionDto) {
        Movie movie = movieService.getMovie(id);
        Excertion excertion = excertionMapper.toEntity(excertionDto);
        excertion.setMovie(movie);
        excertionService.save(excertion);
    }

    @Override
    public void createPersonExcertion(Long id, ExcertionRequestDto excertionDto) {
        Person person = personService.getPerson(id);
        Excertion excertion = excertionMapper.toEntity(excertionDto);
        excertion.setPerson(person);
        excertionService.save(excertion);
    }

    @Override
    public void updateExcertion(Long id, ExcertionRequestDto excertionDto) {
        Excertion excertion = excertionService.getExcertion(id);
        Excertion convertedExcertion = excertionMapper.toEntity(excertionDto);

        boolean exist = excertionService.isExist(id);
        if (exist) {
            convertedExcertion.setId(excertion.getId());
            convertedExcertion.setMovie(excertion.getMovie());
            convertedExcertion.setPerson(excertion.getPerson());
        }

        excertionService.save(convertedExcertion);
    }

    @Override
    public void deleteExcertion(Long id) {
        excertionService.deleteById(id);
    }
}
