package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.dto.ErrorResponse;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.webapp.facade.folder.UserFolderPersonServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "api/user/folders")
@RequiredArgsConstructor
public class UserFolderPersonRestController {

    private final UserFolderPersonServiceFacade userFolderPersonServiceFacade;

    @GetMapping("/persons")
    public ResponseEntity<?> getFolderPersons(@RequestParam Long userId) {
        try {
            List<FolderPersonResponseDto> folderPersons = userFolderPersonServiceFacade.getFolderPersonsByUserId(userId);
            return ResponseEntity.ok(folderPersons);
        } catch (NotFoundEntityException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Пользователь с таким id = " + userId + " не существует");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> createFolderPersons(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        userFolderPersonServiceFacade.createFolderPerson(userId, folderRequestDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("persons/{id}")
    public ResponseEntity<?> deleteFolderPersons(@PathVariable Long id) {
        try {
            userFolderPersonServiceFacade.deleteFolderById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundEntityException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Папки с таким id = " + id + " не существует");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

    }
}
