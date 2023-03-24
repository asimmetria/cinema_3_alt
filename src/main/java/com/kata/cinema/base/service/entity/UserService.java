package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.User;

public interface UserService {

    void update(User user);
    void save(User user);
    void deleteRoleFromUser(Long userId, Long roleId);
    void addRoleToUser(Long userId, Long roleId);

    User getProxyById(long id);

    User getById(long id);

    void offEnable(long id);

    User findByEmail(String email);
}
