package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.MediaTitleResponseDto;
import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.models.enums.MediaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface MediaRepository extends JpaRepository<Media, Long> {

        Page<MediaTitleResponseDto> findAllByEnableTrueAndStatusEqualsOrderByCreatedDesc(Pageable pageable);
        Page<MediaTitleResponseDto> findAllByCategoryIdAndEnableTrueAndStatusEqualsOrderByCreatedDesc(Long categoryId, Pageable pageable);
        Optional<Media> findByIdAndStatusEquals(Long id, MediaStatus status);

}
