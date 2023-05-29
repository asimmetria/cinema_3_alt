package com.kata.cinema.base.webapp.facade.admin.impl;

import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.models.dto.response.ProductionStudiosResponseDto;
import com.kata.cinema.base.service.entity.ProductionStudiosService;
import com.kata.cinema.base.webapp.facade.admin.ProductionStudiosServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductionStudiosServiceFacadeImpl implements ProductionStudiosServiceFacade {
    private final ProductionStudiosService studiosService;

    @Override
    public ProductionStudiosResponseDto getProductionStudiosById(Long id) {
        return studiosService.getProductionStudiosById(id);
    }

    @Override
    public void save(ProductionStudiosRequestDto requestDto) {
        studiosService.save(null, requestDto);
    }

    @Override
    public void update(Long id, ProductionStudiosRequestDto requestDto) {
        studiosService.save(id, requestDto);
    }

    @Override
    public void deleteById(Long id) {
        studiosService.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return studiosService.productionStudiosIsExist(id);
    }
}
