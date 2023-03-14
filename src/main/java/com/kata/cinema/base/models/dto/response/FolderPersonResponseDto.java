package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.FolderPersonType;
import com.kata.cinema.base.models.enums.Privacy;
import lombok.Data;


@Data
public class FolderPersonResponseDto extends FolderResponseDto {
    private FolderPersonType type;

    public FolderPersonResponseDto(Long id, Privacy privacy, String name, String description, FolderPersonType type) {
        super(id, privacy, name, description);
        this.type = type;
    }
}
