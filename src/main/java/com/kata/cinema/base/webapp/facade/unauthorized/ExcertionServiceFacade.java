package com.kata.cinema.base.webapp.facade.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import org.springframework.data.domain.Page;

public interface ExcertionServiceFacade {
    Page<ExcertionResponseDto> getMovieExcertion(Long id, int pageNumber, int size);

    Page<ExcertionResponseDto> getPersonExcertion(Long id, int pageNumber, int size);

    void createMovieExcertion(Long id, ExcertionRequestDto excertionDto);

    void createPersonExcertion(Long id, ExcertionRequestDto excertionDto);

    void updateExcertion(Long id, ExcertionRequestDto excertionDto);

    void deleteExcertion(Long id);
}
