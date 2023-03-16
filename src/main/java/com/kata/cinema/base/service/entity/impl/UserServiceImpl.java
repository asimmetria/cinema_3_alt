package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.repository.RoleRepository;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
        User user = userRepository.getReferenceById(id);
        user.setEnable(enable);
    }

    @Override
    public void deleteRoleFromUser(Long userId, Long roleId) {
       User user = userRepository.getReferenceById(userId);
       Role role = roleRepository.getReferenceById(roleId);
       user.getRoles().remove(role);
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.getReferenceById(userId);
        Role role = roleRepository.getReferenceById(roleId);
        user.getRoles().add(role);
    }

    @Override
    public User getProxyById(long id) {
        return userRepository.getReferenceById(id);
    }
}
