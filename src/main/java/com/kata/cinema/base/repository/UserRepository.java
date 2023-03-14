package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
