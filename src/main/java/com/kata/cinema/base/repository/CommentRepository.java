package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserCommentResponseDto(c.id) " +
            "FROM Comment c WHERE c.media.id = :mediaId")
    List<UserCommentResponseDto> listDto(@Param("mediaId") Long mediaId);

    @Query("SELECT DISTINCT new com.kata.cinema.base.models.entitys.User(c.user.id) FROM Comment c WHERE c.id IN :commentIds")
    List<User> getUsersByCommentIds(@Param("commentIds") List<Long> commentIds);

    default List<UserNameResponseDto> getUserDtoByCommentIds(List<Long> commentIds){
       return null;
   }

    default UserCommentResponseDto getUserCommentById(@Param("commId") Long commId){
       return null;
    }

}