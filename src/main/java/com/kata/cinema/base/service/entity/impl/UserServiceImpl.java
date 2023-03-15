package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteEnableFlagById (Long id, boolean enable) {
        userRepository.deleteEnableFlagById(id,enable);
    }

    @Override
    public void deleteRoleFromUser(Long userId, Long roleId) {
        userRepository.deleteRoleFromUser(userId, roleId);
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        userRepository.addRoleToUser(userId, roleId);
    }

    @Override
    public User getProxyById(long id) {
        return userRepository.getReferenceById(id);
    }
}
