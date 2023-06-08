package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
