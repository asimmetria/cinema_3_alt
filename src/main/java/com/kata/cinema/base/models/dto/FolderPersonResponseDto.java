package com.kata.cinema.base.models.dto;

import com.kata.cinema.base.models.enums.FolderPersonType;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class FolderPersonResponseDto extends FolderResponseDto {
    private FolderPersonType type;
}
