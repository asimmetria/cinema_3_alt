package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long>  {
}
