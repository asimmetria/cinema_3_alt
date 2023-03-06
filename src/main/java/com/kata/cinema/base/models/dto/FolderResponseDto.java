package com.kata.cinema.base.models.dto;

import com.kata.cinema.base.models.enums.Privacy;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public abstract class FolderResponseDto {
    Long id;
    Privacy privacy;
    String name;
    String description;

    public FolderResponseDto() {
    }
}
