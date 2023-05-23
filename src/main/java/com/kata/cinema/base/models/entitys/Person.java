package com.kata.cinema.base.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "preview_url")
    private String previewUrl;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "original_first_name", nullable = false)
    private String originalFirstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "original_last_name")
    private String originalLastName;

    @Column(name = "height")
    private double height;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @OneToMany(mappedBy = "person")
    private Set<Cast> casts;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getOriginalFullName() {
        return originalFirstName + " " + originalLastName;
    }
}
