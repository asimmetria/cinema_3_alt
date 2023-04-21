package com.kata.cinema.base.webapp.rest.excertion;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.webapp.facade.excertion.ExcertionServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excertions")
@AllArgsConstructor
public class ExcertionRestController {
    private final ExcertionServiceFacade excertionServiceFacade;

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExcertion(@PathVariable Long id, @RequestBody ExcertionRequestDto excertionDto) {
        excertionServiceFacade.updateExcertion(id, excertionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcertion(@PathVariable Long id) {
        excertionServiceFacade.deleteExcertion(id);
        return ResponseEntity.ok().build();
    }
}
