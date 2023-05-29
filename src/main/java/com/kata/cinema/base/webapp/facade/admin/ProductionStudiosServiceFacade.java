package com.kata.cinema.base.webapp.facade.admin;

import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.models.dto.response.ProductionStudiosResponseDto;

public interface ProductionStudiosServiceFacade {
    ProductionStudiosResponseDto getProductionStudiosById(Long id);
    void save(ProductionStudiosRequestDto requestDto);
    void update(Long id, ProductionStudiosRequestDto requestDto);
    void deleteById(Long id);
    boolean isExist(Long id);
}
