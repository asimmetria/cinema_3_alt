package com.kata.cinema.base.webapp.facade.unauthorized.impl;

import com.kata.cinema.base.converter.comment.CommentMapper;
import com.kata.cinema.base.models.dto.request.CommentRequestDto;
import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.service.dto.CommentDtoService;
import com.kata.cinema.base.service.entity.CommentService;
import com.kata.cinema.base.service.entity.MediaService;
import com.kata.cinema.base.validation.MediaCommentValidation;
import com.kata.cinema.base.webapp.facade.unauthorized.MediaCommentServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MediaCommentServiceFacadeImpl implements MediaCommentServiceFacade {

    private final MediaCommentValidation mediaCommentValidation;
    private final CommentService commentService;
    private final CommentDtoService commentDtoService;
    private final MediaService mediaService;
    private final CommentMapper commentMapper;

    @Override
    public Page<UserCommentResponseDto> getComments(Long mediaId) {
        return new PageImpl<>(commentDtoService.listWellDoneUserCommentResponseDto(mediaId));
    }

    @Override
    public void sendComment(Long mediaId, CommentRequestDto commentRequestDto) throws Exception {
        mediaCommentValidation.isExistMediaById(mediaId);
        mediaCommentValidation.commentHasParentComment(commentRequestDto);
        Media media = mediaService.getMediaById(mediaId);
        Comment comment = commentMapper.toEntity(commentRequestDto);
        comment.setModerate(false);
        comment.setMedia(media);
        commentService.save(comment);
    }

    @Override
    public void editComment(String message, Long mediaId, Long commentId) throws Exception {
        mediaCommentValidation.isExistMediaById(mediaId);
        mediaCommentValidation.isExistCommentById(commentId);
        mediaCommentValidation.checkMediaIdAndCommentIdHaveSameId(mediaId, commentId);
        Comment comment = commentService.getCommentById(commentId);
        comment.setMessage(message);
        commentService.save(comment);
    }

    @Override
    public void deleteComment(Long mediaId, Long commentId) throws Exception {
        mediaCommentValidation.isExistMediaById(mediaId);
        mediaCommentValidation.isExistCommentById(commentId);
        mediaCommentValidation.checkMediaIdAndCommentIdHaveSameId(mediaId, commentId);
        Comment comment = commentService.getCommentById(commentId);
        commentService.delete(comment);
    }

}
