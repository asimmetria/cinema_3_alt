package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.enums.Privacy;
import lombok.Data;


@Data
public class FolderRequestDto {
    private Privacy privacy;
    private String name;
    private String description;

    public FolderRequestDto() {
    }
}
