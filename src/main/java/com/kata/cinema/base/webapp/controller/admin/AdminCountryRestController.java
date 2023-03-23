package com.kata.cinema.base.webapp.controller.admin;

import com.kata.cinema.base.models.dto.response.CountryResponseDto;
import com.kata.cinema.base.models.entitys.Country;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminCountryRestController {

    private final CountryDtoService countryDtoService;

    private final CountryService countryService;

    @PostMapping("/admin/countries")
    public ResponseEntity<Void> createCountryByName(@RequestParam String name) {
        Country country = new Country();
        country.setName(name);
        countryService.save(country);
        return ResponseEntity.ok().build();
    }

    @PutMapping("admin/countries/{id}")
    public ResponseEntity<Void> putCountryById(@PathVariable Long id, @RequestParam String name) {
        if (countryService.getCountryById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Country country = countryService.getCountryById(id);
        country.setName(name);
        countryService.save(country);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("admin/countries/{id}")
    public ResponseEntity<Void> deleteCountryById(@PathVariable Long id) {
        if (countryService.getProxyById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<List<CountryResponseDto>> getAllCountriesByMovieId(@PathVariable Long id) {
        return ResponseEntity.ok(countryDtoService.getCountriesByMovieId(id));
    }
}
