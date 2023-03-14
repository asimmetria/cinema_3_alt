package com.kata.cinema.base.webapp.controller.user;

import com.kata.cinema.base.converter.folder.FolderPersonMapper;
import com.kata.cinema.base.models.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.entitys.Folder;
import com.kata.cinema.base.models.enums.FolderPersonType;
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
public class UserFolderPersonRestController {

    private final FolderDtoService  folderDtoService;
    private final FolderService folderService;
    private final FolderPersonMapper folderPersonMapper;
    private final UserService userService;

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersons(@RequestParam Long userId) {
        return ResponseEntity.ok(folderDtoService.getPersonFoldersByUserId(userId));
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> createFolderPersons(@RequestParam Long userId, @Valid @RequestBody FolderRequestDto folderRequestDto) {
        if (folderRequestDto.getName() == null) {
            folderRequestDto.setName(FolderPersonType.CUSTOM.getName());
        }

        Folder folder = folderPersonMapper.toEntity(folderRequestDto);
        folder.setUser(userService.getProxyById(userId));
        folderService.save(folder);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<Void> deleteFolderPersons(@PathVariable Long id) {
        folderService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
