package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.Privacy;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "folders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
@Getter
@Setter
@ToString
public abstract class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "privacy")
    private Privacy privacy;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Folder folder)) return false;
        return id == folder.id && userId == folder.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}
