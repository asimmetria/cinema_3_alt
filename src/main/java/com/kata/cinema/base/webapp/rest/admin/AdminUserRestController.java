package com.kata.cinema.base.webapp.rest.admin;

import com.kata.cinema.base.models.dto.request.UserRequestDto;
import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.webapp.facade.user.UserServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api/admin/users")
@RequiredArgsConstructor
public class AdminUserRestController {

    private final UserServiceFacade userServiceFacade;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceFacade.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        userServiceFacade.update(userRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userServiceFacade.save(userRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/enable")
    public ResponseEntity<Void> deleteUserEnableFlag(@PathVariable("id") long id) {
        userServiceFacade.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> addRoleToUser(@RequestParam Long roleId, @PathVariable("id") Long userId) {
        userServiceFacade.addRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleFromUser(@RequestParam Long roleId, @PathVariable("id") Long id) {
        userServiceFacade.deleteRoleFromUser(id, roleId);
        return ResponseEntity.ok().build();
    }
}
