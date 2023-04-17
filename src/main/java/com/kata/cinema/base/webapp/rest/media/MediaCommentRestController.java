package com.kata.cinema.base.webapp.rest.media;

import com.kata.cinema.base.models.dto.request.CommentRequestDto;
import com.kata.cinema.base.models.dto.response.UserCommentResponseDto;
import com.kata.cinema.base.webapp.facade.mediacomment.MediaCommentServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/media/{id}/comments")
@RequiredArgsConstructor
public class MediaCommentRestController {

    private final MediaCommentServiceFacade mediaCommentServiceFacade;

    @GetMapping
    public ResponseEntity<Page<UserCommentResponseDto>> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(mediaCommentServiceFacade.getComments(id));
    }

    @PostMapping
    public ResponseEntity<Void> sendComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDto commentRequestDto) throws Exception {
        mediaCommentServiceFacade.sendComment(id, commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{commentId}")
    public ResponseEntity<Void> editComment(@RequestParam String message, @PathVariable Long id, @PathVariable("commentId") Long commentId) throws Exception {
        mediaCommentServiceFacade.editComment(message, id, commentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable("commentId") Long commentId) throws Exception {
        mediaCommentServiceFacade.deleteComment(id, commentId);
        return ResponseEntity.noContent().build();
    }

}
