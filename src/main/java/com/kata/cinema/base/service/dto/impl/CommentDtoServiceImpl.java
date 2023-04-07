package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.service.dto.CommentDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Long> ids = listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList();
        List<Long> parentIds = listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getParentId).toList();
        List<Integer> levels = listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getLevel).toList();
        List<String> messages = listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getMessage).toList();
        for (Long id : ids){
            result.get(Math.toIntExact(id)).setId(id);
            result.get(Math.toIntExact(id)).setUser(getUserDtoByCommentIds(mediaId).get(Math.toIntExact(id)));
            result.get(Math.toIntExact(id)).setParentId(parentIds.get(Math.toIntExact(id)));
            result.get(Math.toIntExact(id)).setLevel(levels.get(Math.toIntExact(id)));
            result.get(Math.toIntExact(id)).setMessage(messages.get(Math.toIntExact(id)));
        }
        return result;
    }

    @Override
    public List<UserNameResponseDto> getUserDtoByCommentIds(Long mediaId) {
        return commentRepository.getUsersByCommentIds(listRawUserCommentResponseDto(mediaId).stream().map(UserCommentResponseDto::getId).toList());
    }

}
