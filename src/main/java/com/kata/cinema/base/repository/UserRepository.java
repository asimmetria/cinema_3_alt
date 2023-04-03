package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


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

    User findByEmail(String email);
}
