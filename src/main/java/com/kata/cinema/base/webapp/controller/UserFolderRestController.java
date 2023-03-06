package com.kata.cinema.base.webapp.controller;

import com.kata.cinema.base.models.dto.FolderMovieResponseDto;
import com.kata.cinema.base.models.dto.FolderPersonResponseDto;
import com.kata.cinema.base.models.dto.FolderRequestDto;
import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.models.enums.FolderPersonType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping( "api/user/folders")
public class UserFolderRestController {

    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponseDto>> getFolderMovies() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersons() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<FolderRequestDto> createFolderPersons(@Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) folderRequestDto.setName(FolderPersonType.CUSTOM.getName());
        return ResponseEntity.ok(folderRequestDto);
    }


    @PostMapping("/movies")
    public ResponseEntity<FolderRequestDto> createFolderMovies(@Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) folderRequestDto.setName(FolderMovieType.CUSTOM.getName());
        return ResponseEntity.ok(folderRequestDto);
    }

    @DeleteMapping("persons/{id}")
    public void deleteFolderPersons(@RequestBody FolderRequestDto folderRequestDto, @PathVariable("id") long id) {
        assert folderRequestDto != null;
        if (!Objects.equals(folderRequestDto.getName(), FolderMovieType.CUSTOM.getName())) {
            new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("movies/{id}")
    public void deleteFolderMovies(@RequestBody FolderRequestDto folderRequestDto, @PathVariable("id") long id) {
        assert folderRequestDto != null;
        if (!Objects.equals(folderRequestDto.getName(), FolderPersonType.CUSTOM.getName())) {
            new ResponseEntity<>(HttpStatus.OK);
        }
    }


}
