package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.entitys.Movie;
import lombok.Data;

@Data
public class FolderMoviePositionalResponseDto {

    public Long id;
    public Movie movie;
    public Folder folder;
    public Integer positional;

    public FolderMoviePositionalResponseDto() {
    }

    public FolderMoviePositionalResponseDto(Long id, Movie movie, Folder folder, Integer positional) {
        this.id = id;
        this.movie = movie;
        this.folder = folder;
        this.positional = positional;
    }
}
