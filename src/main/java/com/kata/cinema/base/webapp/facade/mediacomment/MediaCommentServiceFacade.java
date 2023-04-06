package com.kata.cinema.base.webapp.facade.mediacomment;

import com.kata.cinema.base.models.dto.request.CommentRequestDto;
import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import org.springframework.data.domain.Page;

public interface MediaCommentServiceFacade {

    Page<UserCommentResponseDto> getComments(Long mediaId);
    void sendComment (Long mediaId, CommentRequestDto commentRequestDto) throws Exception;
    void editComment(String message, Long mediaId, Long commentId) throws Exception;

    void deleteComment(Long mediaId, Long commentId) throws Exception;

}
