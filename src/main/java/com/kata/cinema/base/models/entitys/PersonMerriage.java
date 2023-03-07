package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "persons_marriage")
public class PersonMerriage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "marriageStatus")
    private String marriageStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersonMerriage personMerriage = (PersonMerriage) o;
        return id != null && Objects.equals(id, personMerriage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
