package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.request.MediaRequestDto;
import com.kata.cinema.base.models.dto.response.MediaTitleResponseDto;
import com.kata.cinema.base.models.entitys.Categories;
import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.models.enums.MediaStatus;
import org.springframework.data.domain.Page;

public interface MediaService {

    Page<MediaTitleResponseDto> findEnabledAndVerifiedMedias(Integer pageNumber, Long countItems, Long categoryId);

    void createMedia(Media media);

    void updateMedia(Long id, Media media);

    void changeEnableStatus(Long id);

    void changeStatusWaitVerify(Long id);

    void delete(Long id);

    void publish(Long id);

}
