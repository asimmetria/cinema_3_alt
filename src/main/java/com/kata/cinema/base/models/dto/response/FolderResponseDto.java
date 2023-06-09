package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.Privacy;
import lombok.Data;

@Data
public abstract class FolderResponseDto {
    private Long id;
    private Privacy privacy;
    private String name;
    private String description;

    public FolderResponseDto() {
    }

    public FolderResponseDto(Long id, Privacy privacy, String name, String description) {
        this.id = id;
        this.privacy = privacy;
        this.name = name;
        this.description = description;
    }
}
