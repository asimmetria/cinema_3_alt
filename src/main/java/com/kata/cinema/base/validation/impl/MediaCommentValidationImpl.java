package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.converter.comment.CommentMapper;
import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.dto.request.CommentRequestDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.repository.MediaRepository;
import com.kata.cinema.base.validation.MediaCommentValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MediaCommentValidationImpl implements MediaCommentValidation {

    private final CommentRepository commentRepository;
    private final MediaRepository mediaRepository;

    private final CommentMapper commentMapper;


    @Override
    public void isExistMediaById(long id) {
        log.debug("Start validation is exist media with id = {}", id);

        if (!mediaRepository.existsById(id)) {
            log.error("Media with id = {} is not exist", id);
            throw new NotFoundEntityException(String.format("Медиа с таким id = %s не существует", id));
        }

        log.info("Success validation is exist media with id = {}", id);
    }

    @Override
    public void isExistCommentById(long id) {
        log.debug("Start validation is exist comment with id = {}", id);

        if (!commentRepository.existsById(id)) {
            log.error("Comment with id = {} is not exist", id);
            throw new NotFoundEntityException(String.format("Комментария с таким id = %s не существует", id));
        }

        log.info("Success validation is exist comment with id = {}", id);
    }

    @Override
    public void checkMediaIdAndCommentIdHaveSameId(long mediaId, long commentId) throws Exception {
        log.debug("Check if the transmitted media and the media comment have the same id = {}", mediaId);
        Comment comment = new Comment();
        Optional<Comment> optional = commentRepository.findById(commentId);
        if(optional.isPresent()) {
            comment = optional.get();
        }
        if (!comment.getMedia().getId().equals(mediaId)) {
            log.error("Media id = {} does not match the comment id", mediaId);
            throw new Exception(String.format("Id медиа = %s не совпадает с id комментария", mediaId));
        }
        log.info("Successful check that the comment id matches with the media id = {}", mediaId);

    }

    @Override
    public void commentIsModerate(CommentRequestDto commentRequestDto) throws Exception {
        log.debug("Checking that the comment is being moderated");
        Comment comment = commentMapper.toEntity(commentRequestDto);
        if (!comment.isModerate()) {
            log.error("The comment is not moderated");
            throw new Exception("Комментарий не прошёл модерацию");
        }
        log.info("Successful check that the comment is moderated");
    }

    @Override
    public void commentHasParentComment(CommentRequestDto commentRequestDto) throws Exception {
        log.debug("Checking that the comment has a parent comment");
        Comment comment = commentMapper.toEntity(commentRequestDto);
        if (comment.getParentComment() == null){
            log.error("The comment doesn't have a parent comment");
            throw new Exception("Корневой комментарий не найден");
        }
        log.info("Successful check that the comment has a parent comment");
    }

}
