package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserCommentResponseDto(c.id) " +
            "FROM Comment c WHERE c.media.id = :mediaId")
    List<UserCommentResponseDto> listUserCommentResponseDto(@Param("mediaId") Long mediaId);

    @Query("SELECT new com.kata.cinema.base.models.entitys.User(c.user.id) FROM Comment c WHERE c.id IN :commentIds")
    List<UserNameResponseDto> getUsersByCommentIds(@Param("commentIds") List<Long> commentIds);

}