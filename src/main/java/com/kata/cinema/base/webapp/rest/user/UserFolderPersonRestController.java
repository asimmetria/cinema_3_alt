package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.webapp.facade.folder.UserFolderPersonServiceFacade;
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
public class UserFolderPersonRestController {

    private final UserFolderPersonServiceFacade userFolderPersonServiceFacade;

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersons(@RequestParam Long userId) {
        return ResponseEntity.ok(userFolderPersonServiceFacade.getFolderPersonsByUserId(userId));
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> createFolderPersons(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        userFolderPersonServiceFacade.createFolderPerson(folderRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<Void> deleteFolderPersons(@PathVariable Long id) {
        userFolderPersonServiceFacade.deleteFolderById(id);
        return ResponseEntity.ok().build();
    }
}
