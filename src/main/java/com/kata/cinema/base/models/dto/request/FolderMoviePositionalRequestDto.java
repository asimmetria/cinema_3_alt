package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.entitys.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FolderMoviePositionalRequestDto {

    public Movie movie;
    public Folder folder;
    public Integer positional;


}
