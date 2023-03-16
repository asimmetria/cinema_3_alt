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
    public User getProxyById(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
