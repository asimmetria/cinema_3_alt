package com.kata.cinema.base.validation;

import com.kata.cinema.base.models.dto.request.CommentRequestDto;

public interface MediaCommentValidation {
    void isExistMediaById(long id);
    void isExistCommentById(long id);
    void checkMediaIdAndCommentIdHaveSameId(long id, long commentId) throws Exception;

    void commentHasParentComment(CommentRequestDto commentRequestDto) throws Exception;

}
