package com.kata.cinema.base.models.entitys;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "professions")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Profession profession = (Profession) o;
        return id != null && Objects.equals(id, profession.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

