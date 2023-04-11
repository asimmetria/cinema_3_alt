package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.models.enums.MediaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface MediaRepository extends JpaRepository<Media, Long> {

        Optional<Media> findByIdAndStatusEquals(Long id, MediaStatus status);

}
