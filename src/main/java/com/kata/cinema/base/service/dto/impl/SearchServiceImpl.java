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

        List<SearchMovieDto> movies = movieRepository.findByNameContaining(filterPattern)
            .stream()
            .map(movie -> new SearchMovieDto(
                movie.getId(),
                movie.getName(),
                movie.getOriginalName(),
                movie.getPreviewUrl(),
                movie.getDateRelease(),
                movie.getAvgScore()))
            .limit(3)
            .collect(Collectors.toList());


        List<SearchCollectionDto> collections = collectionRepository.findByNameContaining(filterPattern)
            .stream()
            .map(collection -> new SearchCollectionDto(
                collection.getName(),
                collection.getCollectionUrl(),
                collection.getCountMovies()))
            .limit(3)
            .collect(Collectors.toList());


        List<SearchPersonDto> persons = personRepository.findByFullNameContaining(filterPattern)
            .stream()
            .map(person -> new SearchPersonDto(
                person.getId(),
                person.getPhotoUrl(),
                person.getFullName(),
                person.getOriginalFullName(),
                person.getDateBirth()))
            .limit(3)
            .collect(Collectors.toList());


        return new SearchResponseDto(movies, collections, persons);
    }

}
