package com.kata.cinema.base.webapp.rest.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.webapp.facade.unauthorized.ExcertionServiceFacade;
import com.kata.cinema.base.webapp.facade.unauthorized.PersonServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonRestController {
    private final ExcertionServiceFacade excertionServiceFacade;
    private final PersonServiceFacade personServiceFacade;

    @GetMapping("/{id}")
    public ResponseEntity<PersonViewResponseDto> getPerson(@PathVariable Long id) {
        PersonViewResponseDto personDto = personServiceFacade.getPersonViewById(id);
        if (personDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/excertions/page/{pageNumber}")
    public Page<ExcertionResponseDto> getExcertion(@PathVariable Long id,
                                                   @PathVariable int pageNumber,
                                                   @RequestParam(value = "itemsOnPage", required = false) int size) {
        return excertionServiceFacade.getPersonExcertion(id, pageNumber, size);
    }

    @PostMapping("/{id}/excertions")
    public ResponseEntity<Void> saveExcertion(@PathVariable Long id, @RequestBody ExcertionRequestDto excertionDto) {
        excertionServiceFacade.createPersonExcertion(id, excertionDto);
        return ResponseEntity.ok().build();
    }
}
