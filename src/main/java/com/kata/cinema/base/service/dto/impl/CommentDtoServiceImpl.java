package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.service.dto.CommentDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentDtoServiceImpl implements CommentDtoService {

    private final CommentRepository commentRepository;

    @Override
    public List<UserCommentResponseDto> listRawUserCommentResponseDto(Long mediaId) {
        return commentRepository.listUserCommentResponseDto(mediaId);
    }

    @Override
    public List<UserCommentResponseDto> listWellDoneUserCommentResponseDto(Long mediaId) {
        List<UserCommentResponseDto> result = listRawUserCommentResponseDto(mediaId);
        for (Long id : listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList()){
            Comment comment = new Comment();
            Optional<Comment> optional = commentRepository.findById(id);
            if(optional.isPresent()) comment = optional.get();
            result.get(Math.toIntExact(id)).setId(comment.getId());
            result.get(Math.toIntExact(id)).setUser(getUserDtoByCommentIds(mediaId).get(comment.getUser().getId().intValue()));
            result.get(Math.toIntExact(id)).setParentId(comment.getParentComment().getId());
            result.get(Math.toIntExact(id)).setLevel(comment.getLevel());
            result.get(Math.toIntExact(id)).setMessage(comment.getMessage());
        }
        return result;
    }

    @Override
    public List<UserNameResponseDto> getUserDtoByCommentIds(Long mediaId) {
        return commentRepository.getUsersByCommentIds(listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList());
    }

}
