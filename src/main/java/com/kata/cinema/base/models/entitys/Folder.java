package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.Privacy;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Table(name = "folders")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type_folder")
@Getter
@Setter
public abstract class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @Column(name = "privacy")
    private Privacy privacy;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Folder folder = (Folder) o;
        return id != null && Objects.equals(id, folder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
