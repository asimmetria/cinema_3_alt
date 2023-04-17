package com.kata.cinema.base.webapp.facade.user.impl;

import com.kata.cinema.base.converter.user.UserMapper;
import com.kata.cinema.base.models.dto.request.UserRequestDto;
import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.service.dto.UserDtoService;
import com.kata.cinema.base.service.entity.UserService;
import com.kata.cinema.base.webapp.facade.user.UserServiceFacade;
import com.kata.cinema.base.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceFacadeImpl implements UserServiceFacade {

    private final UserService userService;
    private final UserDtoService userDtoService;
    private final UserMapper userMapper;
    private final UserValidation userValidation;

    @Override
    public void update(UserRequestDto userRequestDto, long id) {
        userValidation.isExistUserById(id);
        User user = userMapper.toEntity(userRequestDto);
        user.setId(id);
        userService.update(user);
    }

    @Override
    public void save(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        userService.save(user);
    }

    @Override
    public void delete(long id) {
        userValidation.isExistUserById(id);
        userService.offEnable(id);
    }

    @Override
    public void addRoleToUser(long roleId, long userId) {
        userValidation.isExistUserById(userId);
        //TODO сделать такую же валидацию на roleId
        userService.addRoleToUser(userId, roleId);
    }

    @Override
    public void deleteRoleFromUser(long roleId, long userId) {
        userValidation.isExistUserById(userId);
        //TODO сделать такую же валидацию на roleId
        userService.deleteRoleFromUser(userId, roleId);
    }

    @Override
    public UserResponseDto getUserById(long id) {
        return userDtoService.getUserById(id);
    }
}
