package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.service.dto.CommentDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentDtoServiceImpl implements CommentDtoService {

    private final CommentRepository commentRepository;

    @Override
    public List<UserCommentResponseDto> listUserCommentResponseDto(Long mediaId) {
        return commentRepository.listUserCommentResponseDto(mediaId);
    }

    @Override
    public List<UserNameResponseDto> getUserDtoByCommentIds(Long mediaId) {
        List<UserNameResponseDto> result = new ArrayList<>();
        List<Long> idUserCommentResponseDto = listUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList();
        for (Long commentId : idUserCommentResponseDto) {
            result.add(new UserNameResponseDto(commentId));
        }
        return result;
    }

    @Override
    public UserCommentResponseDto getUserCommentById(Long mediaId) {
        UserCommentResponseDto userCommentResponseDto = new UserCommentResponseDto();
        Comment comment = new Comment();
        Optional<Comment> optional = commentRepository.findById(mediaId);
        if(optional.isPresent()) comment = optional.get();
        userCommentResponseDto.setId(comment.getId());
        userCommentResponseDto.setUser(getUserDtoByCommentIds(mediaId).get(comment.getUser().getId().intValue()));
        userCommentResponseDto.setParentId(comment.getParentComment().getId());
        userCommentResponseDto.setLevel(comment.getLevel());
        userCommentResponseDto.setMessage(comment.getMessage());
        return userCommentResponseDto;

    }

}
