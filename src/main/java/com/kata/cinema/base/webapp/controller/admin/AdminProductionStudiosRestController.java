package com.kata.cinema.base.webapp.controller.admin;

import com.kata.cinema.base.converter.production.ProductionStudioMapper;
import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.models.entitys.ProductionStudios;
import com.kata.cinema.base.service.entity.ProductionStudiosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/admin/studios")
@AllArgsConstructor
public class AdminProductionStudiosRestController {


    private final ProductionStudiosService studiosService;

    private final ProductionStudioMapper studioMapper;

    @PostMapping
    public ResponseEntity<Void> newProductionStudio(@RequestBody ProductionStudiosRequestDto requestDto) {
        ProductionStudios studio = studioMapper.toEntity(requestDto);
        studiosService.save(studio);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductionStudioById(@PathVariable Long id) {
        if (studiosService.getProductionStudiosById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        studiosService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putProductionStudioById(@RequestBody ProductionStudiosRequestDto requestDto,
                                                        @PathVariable Long id) {
        ProductionStudios convertedStudio = studioMapper.toEntity(requestDto);
        if (studiosService.getProductionStudiosById(id) == null) {
            studiosService.save(convertedStudio);
            return ResponseEntity.ok().build();
        }
        convertedStudio.setId(id);
        studiosService.save(convertedStudio);
        return ResponseEntity.ok().build();
    }
}
