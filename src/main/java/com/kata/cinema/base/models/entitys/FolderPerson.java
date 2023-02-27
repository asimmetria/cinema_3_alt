package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.FolderPersonType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("folders_persons")
public class FolderPerson extends Folder {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FolderPersonType folderPersonType;

}
