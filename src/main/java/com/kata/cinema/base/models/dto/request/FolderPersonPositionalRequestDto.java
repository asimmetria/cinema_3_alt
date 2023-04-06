package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.entitys.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FolderPersonPositionalRequestDto {

    public Person person;
    public Folder folder;
    public Integer positional;

}
