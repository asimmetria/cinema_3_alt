package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.collection.CollectionMovieMapper;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.service.entity.MoviePaginationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MoviePaginationServiceImpl implements MoviePaginationService {

    private final CollectionMovieMapper collectionMovieMapper;

    @Override
    public Page<MovieResponseDto> getPageMovieResponse(List<Movie> movies, int page, int size, CollectionSortType collectionSortType) {

        List<MovieResponseDto> movieResponseDtos = movies.stream().map(collectionMovieMapper::toDto).toList();
        Pageable pageable = PageRequest.of(page, size, collectionSortType.getSortType());
        Page<MovieResponseDto> movieResponsePage = new PageImpl<>(movieResponseDtos, pageable, movieResponseDtos.size());

        return movieResponsePage;
    }

}
