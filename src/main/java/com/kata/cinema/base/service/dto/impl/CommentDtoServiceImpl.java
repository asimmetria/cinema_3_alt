package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.service.dto.CommentDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentDtoServiceImpl implements CommentDtoService {


    private final CommentRepository commentRepository;

    List<Long> ids;

    @Override
    public List<UserCommentResponseDto> listDto(Long mediaId) {
        List<UserCommentResponseDto> userCommentResponseDtos = commentRepository.listDto(mediaId);
        ids = userCommentResponseDtos.stream()
                .map(UserCommentResponseDto::getId)
                .toList();
        return commentRepository.listDto(mediaId);
    }

    @Override
    public List<UserNameResponseDto> getUserDtoByCommentIds() {
        List<User> users = commentRepository.getUsersByCommentIds(ids);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        List<UserNameResponseDto> result = new ArrayList<>();
        for (Long commentId : ids) {
            User user = userMap.get(commentRepository.getOne(commentId).getUser().getId());
            result.add(new UserNameResponseDto(user.getId(), user.getName() + " " + user.getLastName(), ""));
        }
        return result;
    }

    @Override
    public UserCommentResponseDto getUserCommentById(Long mediaId) {
        UserCommentResponseDto userCommentResponseDto = new UserCommentResponseDto();
        Comment comment = commentRepository.findById(mediaId).get();
        userCommentResponseDto.setId(comment.getId());
        userCommentResponseDto.setUser(getUserDtoByCommentIds().get(comment.getUser().getId().intValue()));
        userCommentResponseDto.setParentId(comment.getParentComment().getId());
        userCommentResponseDto.setLevel(comment.getLevel());
        userCommentResponseDto.setMessage(comment.getMessage());
        return userCommentResponseDto;

    }

}
