package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Comment;

public interface CommentService {
    void save(Comment comment);

    Comment getCommentById(long id);

    void delete(Comment comment);

}
