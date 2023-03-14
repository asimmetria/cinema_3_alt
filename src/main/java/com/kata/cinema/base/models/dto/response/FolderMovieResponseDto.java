package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.models.enums.Privacy;
import lombok.Data;

@Data
public class FolderMovieResponseDto extends FolderResponseDto {
    private FolderMovieType type;

    public FolderMovieResponseDto(Long id, Privacy privacy, String name, String description, FolderMovieType type) {
        super(id, privacy, name, description);
        this.type = type;
    }
}
