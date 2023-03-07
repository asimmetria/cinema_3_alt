package com.kata.cinema.base.webapp.controller;

import com.kata.cinema.base.models.dto.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.FolderPersonResponseDto;
import com.kata.cinema.base.models.dto.FolderRequestDto;
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
    public ResponseEntity<List<FolderMovieResponseDto>> getFolderMovies() {
        return ResponseEntity.ok(userFolderService.getFolderMovies());
    }

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersons() {
        return ResponseEntity.ok(userFolderService.getFolderPersons());
    }

    @PostMapping("/persons")
    public ResponseEntity<FolderRequestDto> createFolderPersons(@Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) folderRequestDto.setName(FolderPersonType.CUSTOM.getName());
        //
        return ResponseEntity.ok(folderRequestDto);
    }


    @PostMapping("/movies")
    public ResponseEntity<FolderRequestDto> createFolderMovies(@Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) folderRequestDto.setName(FolderMovieType.CUSTOM.getName());
        //
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
