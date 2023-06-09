package com.kata.cinema.base.webapp.rest;

import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.service.entity.ProfessionService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/admin/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }


    @PostMapping
    public ResponseEntity<?> createProfession(@RequestParam String name) {
        ProfessionResponseDto response = professionService.createProfession(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfession(@PathVariable Long id) {
        try {
            professionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfession(@PathVariable Long id, @RequestParam String name) {
        try {
            ProfessionResponseDto response = professionService.updateProfession(id, name);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProfessions() {
        List<ProfessionResponseDto> response = professionService.getAllProfessions();
        return ResponseEntity.ok(response);
    }

}
