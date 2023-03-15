package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("DELETE FROM User ur WHERE ur.id = :userId AND ur.roles = (SELECT r FROM Role r WHERE r.id = :roleId)")
    void deleteRoleFromUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Modifying
    @Query("UPDATE User u SET u.roles = (SELECT r FROM Role r WHERE r.id = :roleId) WHERE u.id = :userId")
    void addRoleToUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Modifying
    @Query("UPDATE User SET User.enable = :enable where User.id = :userId")
    void deleteEnableFlagById(@Param("userId") Long userId, @Param("enable") boolean enable);

    @Query("SELECT new com.kata.cinema.base.models.dto.response.UserResponseDto(" +
            "us.id," +
            "us.email," +
            "us.name," +
            "us.lastName," +
            "us.password," +
            "us.birthday) FROM User us WHERE us.id = :userId")
    UserResponseDto getUserById(@Param("userId") Long userId);
}
