package com.kata.cinema.base.service.entity;


import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.models.dto.response.ProductionStudiosResponseDto;

public interface ProductionStudiosService {

    boolean productionStudiosIsExist(Long id);

    ProductionStudiosResponseDto getProductionStudiosById(Long id);

    void save(Long id, ProductionStudiosRequestDto requestDto);

    void deleteById(Long id);

}
