package com.kata.cinema.base.service.entity.impl;


import com.kata.cinema.base.models.entitys.ProductionStudios;
import com.kata.cinema.base.repository.ProductionStudiosRepository;
import com.kata.cinema.base.service.entity.ProductionStudiosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductionStudiosServiceImpl implements ProductionStudiosService {

    private final ProductionStudiosRepository studiosRepository;

    @Override
    public ProductionStudios getProductionStudiosById(Long id) {
        return studiosRepository.getReferenceById(id);
    }

    @Override
    public void save(ProductionStudios productionStudio) {
        studiosRepository.save(productionStudio);
    }

    @Override
    public void deleteById(Long id) {
        studiosRepository.deleteById(id);
    }

}
