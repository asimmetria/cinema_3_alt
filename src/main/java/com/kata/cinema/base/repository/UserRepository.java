package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserNameResponseDto;
import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserResponseDto(" +
            "us.id," +
            "us.email," +
            "us.name," +
            "us.lastName," +
            "us.password," +
            "us.birthday) FROM User us WHERE us.id = :userId")
    UserResponseDto getUserById(@Param("userId") Long userId);

    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserNameResponseDto(c.user.id) FROM Comment c WHERE c.id IN :commentIds")
    List<UserNameResponseDto> getUsersByCommentIds(@Param("commentIds") List<Long> commentIds);

    Optional<User> findByEmail(String email);
}
