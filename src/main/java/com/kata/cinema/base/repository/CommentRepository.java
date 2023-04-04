package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.entitys.Comment;
import com.kata.cinema.base.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserCommentResponseDto(c.id) " +
            "FROM Comment c WHERE c.parentComment.id = :parentId")
    List<UserCommentResponseDto> listDto(@Param("parentId") Long parentId);

    @Query("SELECT c.id FROM Comment c WHERE c.user.id = :parentId")
    List<Long> commentIds(@Param("parentId") Long parentId);

    @Query("SELECT DISTINCT c.user FROM Comment c WHERE c.id IN :commentIds")
    List<User> getUsersByCommentIds(@Param("commentIds") List<Long> commentIds);

    default List<UserNameResponseDto> getUserDtoByCommentIds(List<Long> commentIds) {
        List<User> users = getUsersByCommentIds(commentIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        List<UserNameResponseDto> result = new ArrayList<>();
        for (Long commentId : commentIds) {
            User user = userMap.get(getOne(commentId).getUser().getId());
            result.add(new UserNameResponseDto(user.getId(), user.getName() + " " + user.getLastName(), ""));
        }
        return result;
    }

    default UserCommentResponseDto getUserCommentById(@Param("commId") Long commId) {
        UserCommentResponseDto userCommentResponseDto = new UserCommentResponseDto();
        Comment comment = findById(commId).get();
        userCommentResponseDto.setId(comment.getId());
        userCommentResponseDto.setUser(getUserDtoByCommentIds(commentIds(commId)).get(Math.toIntExact(commId)));
        userCommentResponseDto.setParentId(comment.getParentComment().getId());
        userCommentResponseDto.setLevel(comment.getLevel());
        userCommentResponseDto.setMessage(comment.getMessage());
        return userCommentResponseDto;
    }

}