package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.FolderPersonType;
import lombok.Data;


@Data
public class FolderPersonResponseDto extends FolderResponseDto {
    private FolderPersonType type;
}