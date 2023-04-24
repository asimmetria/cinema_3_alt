package com.kata.cinema.base.webapp.rest.unauthorized;

import com.kata.cinema.base.models.dto.response.MediaBodyResponseDto;
import com.kata.cinema.base.webapp.facade.publicist.MediaServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medias")
@AllArgsConstructor
public class MediaRestController {
    private final MediaServiceFacade mediaServiceFacade;

    @GetMapping("/{id}")
    public ResponseEntity<MediaBodyResponseDto> getMedia(@PathVariable Long id) {
        MediaBodyResponseDto mediaDto = mediaServiceFacade.getMedia(id);
        return new ResponseEntity<>(mediaDto, HttpStatus.OK);
    }
}
