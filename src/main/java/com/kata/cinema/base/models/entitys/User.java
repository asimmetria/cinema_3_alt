package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "first_name", nullable = false)
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "enable")
    private boolean enable;

    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && enable == user.enable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enable);
    }
}
