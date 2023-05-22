package com.kata.cinema.base.service.entity.impl;






import com.kata.cinema.base.converter.production.ProductionStudioMapper;
import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.models.dto.response.ProductionStudiosResponseDto;
import com.kata.cinema.base.models.entitys.ProductionStudios;
import com.kata.cinema.base.repository.ProductionStudiosRepository;
import com.kata.cinema.base.service.entity.ProductionStudiosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductionStudiosServiceImpl implements ProductionStudiosService {

    private final ProductionStudiosRepository studiosRepository;
    private final ProductionStudioMapper studioMapper;


    @Override
    public ProductionStudiosResponseDto getProductionStudiosById(Long id) {
        ProductionStudios productionStudios = studiosRepository.getProductionStudiosById(id);
        return studioMapper.toDto(productionStudios);
    }

    @Override
    public void save(Long id, ProductionStudiosRequestDto requestDto) {
        ProductionStudios convertedStudio = studioMapper.toEntity(requestDto);

        if (productionStudiosIsExist(id)) {
            convertedStudio.setId(id);
        }
        studiosRepository.save(convertedStudio);
    }

    @Override
    public void deleteById(Long id) {
        studiosRepository.deleteById(id);
    }

    @Override
    public boolean productionStudiosIsExist(Long id) {
        return studiosRepository.existsProductionStudiosById(id);
    }

}

