package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.repository.GenreRepository;
import com.kata.cinema.base.service.entity.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Genre getGenre(Long id) {
        return genreRepository.getGenreById(id);
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
