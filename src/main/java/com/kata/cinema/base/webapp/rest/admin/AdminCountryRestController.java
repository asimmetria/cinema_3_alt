package com.kata.cinema.base.webapp.rest.admin;

import com.kata.cinema.base.models.dto.response.CountryResponseDto;
import com.kata.cinema.base.service.dto.CountryDtoService;
import com.kata.cinema.base.service.entity.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RestController
@RequestMapping("/api/admin/countries")
@RequiredArgsConstructor
public class AdminCountryRestController {

    private final CountryDtoService countryDtoService;
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<String> createCountryByName(@RequestParam String name) {
        if (countryService.isExistCountryByName(name)) {
            return ResponseEntity.badRequest().build();
        }
        countryService.saveCountryByName(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCountryById(@PathVariable Long id,
                                                 @RequestParam String name) {
        if (!countryService.isExistCountryById(id)) {
            return ResponseEntity.notFound().build();
        }
        countryService.update(id, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long id) {
        if (!countryService.isExistCountryById(id)) {
            return ResponseEntity.notFound().build();
        }
        countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //TODO вынести метод в отдельный класс CountryRestController в пакет unauthorized c url /api/countries/{id} - подправить и перенести тест
    @GetMapping("/{id}")
    public ResponseEntity<List<CountryResponseDto>> getAllCountriesByMovieId(@PathVariable Long id) {
        if (!countryService.existsCountryByMovieId(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryDtoService.getCountriesByMovieId(id));
    }
}
