package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.service.dto.CommentDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentDtoServiceImpl implements CommentDtoService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserCommentResponseDto> listRawUserCommentResponseDto(Long mediaId) {
        return commentRepository.listUserCommentResponseDto(mediaId);
    }

    @Override
    public List<UserCommentResponseDto> listWellDoneUserCommentResponseDto(Long mediaId) {
        List<UserCommentResponseDto> result = listRawUserCommentResponseDto(mediaId);
        List<Long> commentIds = listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList();
        List<UserNameResponseDto> users = userRepository.getUsersByCommentIds(commentIds);

        for (UserCommentResponseDto comment : result) comment.setUser(getUserNameResponseDto(users, comment.getId()));
        return result;
    }

    private UserNameResponseDto getUserNameResponseDto(List<UserNameResponseDto> users, long commentId) {
        UserNameResponseDto userNameResponseDto = new UserNameResponseDto(commentId);
        Optional<UserNameResponseDto> optional = users.stream().filter(user -> user.commentId.equals(commentId)).findFirst();
        if (optional.isPresent()) {
            userNameResponseDto = optional.get();
        }
        return userNameResponseDto;
    }

    @Override
    public List<UserNameResponseDto> getUserDtoByCommentIds(Long mediaId) {
        return userRepository.getUsersByCommentIds(listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList());
    }

}
