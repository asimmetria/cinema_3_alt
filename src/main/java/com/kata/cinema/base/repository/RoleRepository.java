package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(RoleNameEnum nameEnum);

}