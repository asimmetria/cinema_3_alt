package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.FolderMovieType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("folders_movies")
public class FolderMovie extends Folder {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FolderMovieType folderMovieType;

}
