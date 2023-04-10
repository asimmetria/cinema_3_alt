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

        List<Long> parentIds = result.stream().map(UserCommentResponseDto::getParentId).toList();
        List<Integer> levels = result.stream().map(UserCommentResponseDto::getLevel).toList();
        List<String> messages = result.stream().map(UserCommentResponseDto::getMessage).toList();


        for (UserCommentResponseDto comment : result) {
            comment.setUser(getUserNameResponseDto(users, comment.getId()));
            comment.setParentId(getParentId(parentIds, comment.getParentId()));
            comment.setLevel(getLevel(levels, comment.getLevel()));
            comment.setMessage(getMessage(messages, comment.getMessage()));
        }
        return result;
    }

    private String getMessage(List<String> messages, String commentMessage){
        return messages.stream().filter(message -> message.equals(commentMessage)).findFirst().orElseThrow();
    }

    private Integer getLevel(List<Integer> levels, int commentLevel){
        return levels.stream().filter(level -> level.equals(commentLevel)).findFirst().orElseThrow();
    }

    private Long getParentId(List<Long> parentIds, long commentParentId){
        return parentIds.stream().filter(parentId -> parentId.equals(commentParentId)).findFirst().orElseThrow();
    }

    private UserNameResponseDto getUserNameResponseDto (List<UserNameResponseDto> users, long commentId) {
        UserNameResponseDto userNameResponseDto = new UserNameResponseDto(commentId);
        Optional<UserNameResponseDto> optional = users.stream().filter(user -> user.commentId.equals(commentId)).findFirst();
        if(optional.isPresent()) {
            userNameResponseDto =  optional.get();
        }
        return userNameResponseDto;
    }

    @Override
    public List<UserNameResponseDto> getUserDtoByCommentIds(Long mediaId) {
        return userRepository.getUsersByCommentIds(listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList());
    }

}
