package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.webapp.facade.movie.UserFolderMoviePositionalServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user/folders")
@RequiredArgsConstructor
public class UserFolderMoviePositionalRestController {

    private final UserFolderMoviePositionalServiceFacade userMoviePositionalServiceFacade;

    @PostMapping("/{id}/movies/{movieId}")
    public ResponseEntity<Void> addMovieToFolder(@PathVariable Long id, @PathVariable Long movieId) {
        userMoviePositionalServiceFacade.createFolderMoviePositional(id, movieId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/movies/{movieId}")
    public ResponseEntity<Void> deleteMovieFromFolder(@PathVariable Long id, @PathVariable Long movieId) {
        FolderMoviePositional folderMoviePositional =
                userMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        userMoviePositionalServiceFacade.deleteFolderMoviePositionalById(folderMoviePositional.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/movies/{movieId}")
    public ResponseEntity<Void> changeMoviePositionInFolder(@PathVariable Long id,
                                                            @PathVariable Long movieId,
                                                            @RequestParam Integer position) {
        userMoviePositionalServiceFacade.updateFolderMoviePositional(id, movieId, position);
        return ResponseEntity.ok().build();
    }

}
