package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import com.kata.cinema.base.repository.RoleRepository;
import com.kata.cinema.base.service.entity.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(RoleNameEnum name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

}
