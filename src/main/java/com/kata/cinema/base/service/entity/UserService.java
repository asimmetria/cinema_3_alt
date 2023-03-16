package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.User;

public interface UserService {

    void update(User user);
    void save(User user);
    void deleteRoleFromUser(Long userId, Long roleId);
    void addRoleToUser(Long userId, Long roleId);
    void deleteEnableFlagById(Long id, boolean enable);
    User getProxyById(long id);
    void save(User user);

    User findByEmail(String email);
}
