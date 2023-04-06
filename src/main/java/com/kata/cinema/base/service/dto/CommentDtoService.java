package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;

import java.util.List;

public interface CommentDtoService {

    List<UserCommentResponseDto> listRawUserCommentResponseDto(Long mediaId);
    List<UserCommentResponseDto> listWellDoneUserCommentResponseDto(Long mediaId);
    List<UserNameResponseDto> getUserDtoByCommentIds(Long mediaId);

}
