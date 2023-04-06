package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.entitys.Person;
import lombok.Data;

@Data
public class FolderPersonPositionalResponseDto {
    public Long id;
    public Person person;
    public Folder folder;
    public Integer positional;

    public FolderPersonPositionalResponseDto() {
    }

    public FolderPersonPositionalResponseDto(Long id, Person person, Folder folder, Integer positional) {
        this.id = id;
        this.person = person;
        this.folder = folder;
        this.positional = positional;
    }
}
