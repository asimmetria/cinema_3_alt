package com.kata.cinema.base.webapp.rest.collection;

import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.webapp.facade.collection.CollectionServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionRestController {

    private final CollectionServiceFacade collectionServiceFacade;

    @GetMapping
    ResponseEntity<List<CollectionResponseDto>> getCollections(@RequestParam(required = false) Long categoryId) {
        if (categoryId == null) {
            return ResponseEntity.ok(collectionServiceFacade.getAllCollections());
        }
        return ResponseEntity.ok(collectionServiceFacade.getCollectionsByCategoryId(categoryId));
    }

    @PostMapping
    ResponseEntity<Void> newCollection(@RequestBody CollectionRequestDto requestDto) {
        collectionServiceFacade.save(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> putCollection(@RequestBody CollectionRequestDto requestDto,
                                       @PathVariable Long id) {
        collectionServiceFacade.update(requestDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionServiceFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
