package com.kata.cinema.base.converter.comment;

import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.converter.config.ConfigMapper;
import com.kata.cinema.base.models.dto.request.CommentRequestDto;
import com.kata.cinema.base.models.entitys.Comment;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface CommentMapper extends  EntityMapper<CommentRequestDto, Comment> {
}
