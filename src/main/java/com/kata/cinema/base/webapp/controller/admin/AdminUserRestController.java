package com.kata.cinema.base.webapp.controller.admin;


import com.kata.cinema.base.converter.EntityMapper;
import com.kata.cinema.base.models.dto.request.UserRequestDto;
import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import com.kata.cinema.base.service.dto.UserDtoService;
import com.kata.cinema.base.service.entity.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping( "api/admin/users")
@RequiredArgsConstructor
public class AdminUserRestController {
    private final UserService userService;
    private final UserDtoService userDtoService;
    private final EntityMapper<UserRequestDto, User> entityMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userDtoService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        User user = entityMapper.toEntity(userRequestDto);
        user.setId(id);
        userService.update(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = entityMapper.toEntity(userRequestDto);
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
        role.setName(RoleNameEnum.USER);
        roleSet.add(role);
        user.setRoles(roleSet);
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserEnableFlag(@RequestParam boolean enable, @PathVariable("id") long id) {
        userService.deleteEnableFlagById(id, enable);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> addRoleToUser(@RequestParam Long roleId, @PathVariable("id") Long userId) {
        userService.addRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleFromUser(@RequestParam Long roleId, @PathVariable("id") Long id) {
        userService.deleteRoleFromUser(id, roleId);
        return ResponseEntity.ok().build();
    }

}
