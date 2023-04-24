package com.kata.cinema.base.webapp.facade.publicist;

import com.kata.cinema.base.models.dto.request.MediaRequestDto;
import com.kata.cinema.base.models.dto.response.MediaBodyResponseDto;

public interface MediaServiceFacade {
    MediaBodyResponseDto getMedia(Long id);

    void createMedia(MediaRequestDto mediaRequestDto);

    void updateMedia(Long id, MediaRequestDto mediaRequestDto);

    void delete(Long id);

    void publish(Long id);
}
