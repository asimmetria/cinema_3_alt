package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.converter.folder.FolderMovieMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.service.dto.FolderDtoService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.service.entity.UserService;
import com.kata.cinema.base.webapp.facade.folder.UserFolderMovieServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "api/user/folders")
@RequiredArgsConstructor
public class UserFolderMovieRestController {

    private final UserFolderMovieServiceFacade userFolderMovieServiceFacade;

    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponseDto>> getFolderMovies(@RequestParam Long userId) {
        return ResponseEntity.ok(userFolderMovieServiceFacade.getFolderMoviesByUserId(userId));
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> createFolderMovies(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        userFolderMovieServiceFacade.createFolderMovies(folderRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("movies/{id}")
    public ResponseEntity<Void> deleteFolderMovies(@PathVariable Long id) {
        userFolderMovieServiceFacade.deleteFolderById(id);
        return ResponseEntity.ok().build();
    }
}
