package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.repository.CommentRepository;
import com.kata.cinema.base.service.entity.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        Comment comment = new Comment();
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            comment = optional.get();
        }
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
