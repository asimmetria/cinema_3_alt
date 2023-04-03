package com.kata.cinema.base.converter.user;

import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.UserRequestDto;
import com.kata.cinema.base.models.entitys.User;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface UserMapper extends EntityMapper<UserRequestDto, User> {
}
