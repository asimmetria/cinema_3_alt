package com.kata.cinema.base.models.dto;

import com.kata.cinema.base.models.enums.FolderMovieType;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class FolderMovieResponseDto extends FolderResponseDto {
    private FolderMovieType type;
}
