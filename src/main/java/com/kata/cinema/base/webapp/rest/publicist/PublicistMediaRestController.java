package com.kata.cinema.base.webapp.rest.publicist;

import com.kata.cinema.base.models.dto.request.MediaRequestDto;
import com.kata.cinema.base.models.dto.response.MediaTitleResponseDto;
import com.kata.cinema.base.models.enums.MediaStatus;
import com.kata.cinema.base.webapp.facade.movie.MediaServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PublicistMediaRestController {

    private final MediaServiceFacade mediaServiceFacade;

    @GetMapping("/medias/page/{pageNumber}")
    public ResponseEntity<Page<MediaTitleResponseDto>> getMedias(
            @RequestParam(defaultValue = "0")  @PathVariable int pageNumber,
            @RequestParam(defaultValue = "10") Long countItems,
            @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(mediaServiceFacade.findEnabledAndVerifiedMedias(pageNumber, countItems, categoryId));
    }

    @PostMapping("/publicist/medias")
    public ResponseEntity<Void> createMedia(@RequestBody MediaRequestDto mediaRequestDto) {
        mediaServiceFacade.createMedia(mediaRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/publicist/medias/{id}")
    public ResponseEntity<Void> updateMedia(@PathVariable Long id, @RequestBody MediaRequestDto mediaRequestDto) {
        mediaServiceFacade.updateMedia(id, mediaRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/publicist/medias/{id}/enable")
    public ResponseEntity<Void> toggleMediaEnabled(@PathVariable Long id) {
        mediaServiceFacade.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/publicist/medias/{id}/publish")
    public ResponseEntity<Void> publishMedia(@PathVariable Long id) {
        mediaServiceFacade.publish(id);
        return ResponseEntity.ok().build();
    }
}
