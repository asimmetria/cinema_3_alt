package com.kata.cinema.base.webapp.controller.user;

import com.kata.cinema.base.converter.folder.FolderMovieMapper;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.service.dto.FolderDtoService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.service.entity.UserService;
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

    private final FolderDtoService folderDtoService;
    private final FolderService folderService;
    private final FolderMovieMapper folderMovieMapper;
    private final UserService userService;

    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponseDto>> getFolderMovies(@RequestParam Long userId) {
        return ResponseEntity.ok(folderDtoService.getMovieFoldersByUserId(userId));
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> createFolderMovies(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) {
            folderRequestDto.setName(FolderMovieType.CUSTOM.getName());
        }
        Folder folder = folderMovieMapper.toEntity(folderRequestDto);
        folder.setUser(userService.getProxyById(userId));
        folderService.save(folder);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("movies/{id}")
    public ResponseEntity<Void> deleteFolderMovies(@PathVariable Long id) {
        folderService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
