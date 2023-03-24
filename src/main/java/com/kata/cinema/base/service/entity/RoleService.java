package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.enums.RoleNameEnum;

public interface RoleService {
    Role findByName(RoleNameEnum name);
    void save(Role role);
}