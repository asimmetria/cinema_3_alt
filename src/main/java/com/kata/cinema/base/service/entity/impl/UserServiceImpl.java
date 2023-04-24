package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import com.kata.cinema.base.repository.RoleRepository;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        Role role = roleRepository.getRoleByName(RoleNameEnum.USER);
        user.setRoles(Set.of(role));
        userRepository.save(user);
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

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Пользователя не существует"));
    }

    @Override
    public void offEnable(long id) {
        User user = userRepository.findById(id).get();
        user.setEnable(false);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s not found", email)));
    }
}
