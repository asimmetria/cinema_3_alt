package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserCommentResponseDto(" +
            "comm.id," +
            "comm.parentComment.id," +
            "comm.level," +
            "comm.message," +
            "UserNameResponseDto(comm.user.id, comm.user.name)) " +
            "FROM Comment comm WHERE comm.id = :commId")
    UserCommentResponseDto getUserCommentById(@Param("commId") Long commId);
}
