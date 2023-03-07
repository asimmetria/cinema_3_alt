package com.kata.cinema.base.models.dto;

import com.kata.cinema.base.models.enums.Privacy;
import lombok.Data;


@Data
public class FolderRequestDto {
    public Privacy privacy;
    public String name;
    public String description;

    public FolderRequestDto() {
    }
}
