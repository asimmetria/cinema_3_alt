package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;

public interface CommentDtoService {
    UserCommentResponseDto getUserCommentById(Long parentId);
}
