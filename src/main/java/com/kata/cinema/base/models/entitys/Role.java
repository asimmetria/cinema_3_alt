package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.RoleNameEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "RoleNameEnum", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleNameEnum roleNameEnum;

    public Role() {
    }

}
