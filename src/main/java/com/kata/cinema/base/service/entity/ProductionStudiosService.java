package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.ProductionStudios;

public interface ProductionStudiosService {

    ProductionStudios getProductionStudiosById(Long id);

    void save(ProductionStudios productionStudio);

    void deleteById(Long id);

}
