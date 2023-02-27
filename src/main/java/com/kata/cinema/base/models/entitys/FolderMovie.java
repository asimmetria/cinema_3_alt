package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.FolderMovieType;
import jakarta.persistence.*;
import lombok.*;

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
