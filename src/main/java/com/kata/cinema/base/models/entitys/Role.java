package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.RoleNameEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
public class Role {
    @Id
    private int id;

    @Column(name = "RoleNameEnum", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleNameEnum roleNameEnum;

    public Role() {
    }

}
