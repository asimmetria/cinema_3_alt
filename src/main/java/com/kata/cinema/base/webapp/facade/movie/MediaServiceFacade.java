package com.kata.cinema.base.webapp.facade.movie;


import com.kata.cinema.base.models.dto.request.MediaRequestDto;
import com.kata.cinema.base.models.dto.response.MediaTitleResponseDto;
import com.kata.cinema.base.models.entitys.Media;
import org.springframework.data.domain.Page;

public interface MediaServiceFacade {

    void createMedia(MediaRequestDto mediaRequestDto);

    void updateMedia(Long id, MediaRequestDto mediaRequestDto);

    void delete(Long id);

    void publish(Long id);

}
