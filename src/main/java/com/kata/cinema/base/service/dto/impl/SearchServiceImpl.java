package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.request.SearchCollectionDto;
import com.kata.cinema.base.models.dto.request.SearchMovieDto;
import com.kata.cinema.base.models.dto.request.SearchPersonDto;
import com.kata.cinema.base.models.dto.response.SearchResponseDto;
import com.kata.cinema.base.repository.CollectionRepository;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.service.dto.SearchService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    private final MovieRepository movieRepository;
    private final CollectionRepository collectionRepository;
    private final PersonRepository personRepository;

    public SearchServiceImpl(MovieRepository movieRepository,
        CollectionRepository collectionRepository,
        PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.collectionRepository = collectionRepository;
        this.personRepository = personRepository;
    }

    @Override
    public SearchResponseDto search(String filterPattern) {
        Pageable pageable = PageRequest.of(0, 3);

        List<SearchMovieDto> movies = movieRepository.findByNameContaining(filterPattern, pageable);
        List<SearchCollectionDto> collections = collectionRepository.findByNameContaining(filterPattern, pageable);
        List<SearchPersonDto> persons = personRepository.findByFullNameContaining(filterPattern, pageable);

        return new SearchResponseDto(movies, collections, persons);
    }

}
