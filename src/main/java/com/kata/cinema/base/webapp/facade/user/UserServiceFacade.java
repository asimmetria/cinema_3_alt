package com.kata.cinema.base.webapp.facade.user;

import com.kata.cinema.base.models.dto.request.UserRequestDto;
import com.kata.cinema.base.models.dto.response.UserResponseDto;

public interface UserServiceFacade {

    void update(UserRequestDto userRequestDto, long id);

    void save(UserRequestDto userRequestDto);

    void delete(long id);

    void addRoleToUser(long roleId, long userId);

    void deleteRoleFromUser(long roleId, long userId);

    UserResponseDto getUserById(long id);
}
