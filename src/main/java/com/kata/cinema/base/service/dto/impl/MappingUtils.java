package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.FolderPersonResponseDto;
import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.models.entitys.FolderPerson;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    //из entity в dto
    public FolderMovieResponseDto mapToMovieDto(FolderMovie entity){
        FolderMovieResponseDto dto = new FolderMovieResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getFolderMovieType());
        dto.setDescription(entity.getDescription());
        dto.setPrivacy(entity.getPrivacy());
        return dto;
    }
    //из dto в entity
    public FolderMovie mapToMovieEntity(FolderMovieResponseDto dto){
        FolderMovie entity = new FolderMovie();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setFolderMovieType(dto.getType());
        entity.setDescription(dto.getDescription());
        entity.setPrivacy(dto.getPrivacy());
        return entity;
    }

    public FolderPersonResponseDto mapToPersonDto(FolderPerson entity){
        FolderPersonResponseDto dto = new FolderPersonResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getFolderPersonType());
        dto.setDescription(entity.getDescription());
        dto.setPrivacy(entity.getPrivacy());
        return dto;
    }
    //из dto в entity
    public FolderPerson mapToPersonEntity(FolderPersonResponseDto dto){
        FolderPerson entity = new FolderPerson();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setFolderPersonType(dto.getType());
        entity.setDescription(dto.getDescription());
        entity.setPrivacy(dto.getPrivacy());
        return entity;
    }

}