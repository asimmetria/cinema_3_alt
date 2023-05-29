package com.kata.cinema.base.webapp.rest.unauthorized;

import com.kata.cinema.base.models.dto.response.CountryResponseDto;
import com.kata.cinema.base.service.dto.CountryDtoService;
import com.kata.cinema.base.service.entity.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries/{id}")
public class CountryRestController {
    private final CountryService countryService;
    private final CountryDtoService countryDtoService;

    public CountryRestController(CountryService countryService, CountryDtoService countryDtoService) {
        this.countryService = countryService;
        this.countryDtoService = countryDtoService;
    }

    @GetMapping
    public ResponseEntity<List<CountryResponseDto>> getAllCountriesByMovieId(@PathVariable Long id) {
        if (!countryService.existsCountryByMovieId(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryDtoService.getCountriesByMovieId(id));
    }
}
