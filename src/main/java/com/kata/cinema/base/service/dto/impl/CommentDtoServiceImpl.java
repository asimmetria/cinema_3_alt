package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.service.dto.CommentDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentDtoServiceImpl implements CommentDtoService {
    private final CommentRepository commentRepository;

    @Override
    public UserCommentResponseDto getUserCommentById(Long parentId) {
        return commentRepository.getUserCommentById(parentId);
    }
}
