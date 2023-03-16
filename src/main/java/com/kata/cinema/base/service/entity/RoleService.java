package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Role;

public interface RoleService {
    Role findByName(String name);
    void save(Role role);
}