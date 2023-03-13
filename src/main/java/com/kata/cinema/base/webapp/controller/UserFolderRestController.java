package com.kata.cinema.base.webapp.controller;

import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.models.enums.FolderPersonType;
import com.kata.cinema.base.service.dto.impl.UserFolderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping( "api/user/folders")
public class UserFolderRestController {

    private final UserFolderService userFolderService;

    public UserFolderRestController(UserFolderService userFolderService) {
        this.userFolderService = userFolderService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponseDto>> getFolderMovies(@RequestParam Long userId) {
        return ResponseEntity.ok(userFolderService.getMovieFoldersByUserId(userId));
    }

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersons(@RequestParam Long userId) {
        return ResponseEntity.ok(userFolderService.getPersonFoldersByUserId(userId));
    }

    @PostMapping("/persons")
    public ResponseEntity<FolderRequestDto> createFolderPersons(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) folderRequestDto.setName(FolderPersonType.CUSTOM.getName());
        userFolderService.createFolderPersons(folderRequestDto, userId);
        return ResponseEntity.ok(folderRequestDto);
    }


    @PostMapping("/movies")
    public ResponseEntity<FolderRequestDto> createFolderMovies(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) folderRequestDto.setName(FolderMovieType.CUSTOM.getName());
        userFolderService.createFolderMovies(folderRequestDto, userId);
        return ResponseEntity.ok(folderRequestDto);
    }

    @DeleteMapping("persons/{id}")
    public void deleteFolderPersons(@RequestBody FolderPersonResponseDto folderPersonResponseDto) {
        assert folderPersonResponseDto != null;
        if (!Objects.equals(folderPersonResponseDto.getName(), FolderMovieType.CUSTOM.getName())) {
            userFolderService.deleteFolderPersons(folderPersonResponseDto);
            new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("movies/{id}")
    public void deleteFolderMovies(@RequestBody FolderMovieResponseDto folderMovieResponseDto) {
        assert folderMovieResponseDto != null;
        if (!Objects.equals(folderMovieResponseDto.getName(), FolderPersonType.CUSTOM.getName())) {
            userFolderService.deleteFolderMovies(folderMovieResponseDto);
            new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
