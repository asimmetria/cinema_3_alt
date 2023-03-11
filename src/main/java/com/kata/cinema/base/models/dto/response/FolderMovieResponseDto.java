package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.FolderMovieType;
import lombok.Data;

@Data
public class FolderMovieResponseDto extends FolderResponseDto {
    private FolderMovieType type;
}
