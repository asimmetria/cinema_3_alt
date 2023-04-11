package com.kata.cinema.base.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.service.entity.RoleService;
import com.kata.cinema.base.service.entity.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${run.init:true}")
@RequiredArgsConstructor
public class TestDataInitializer {
    private final RoleNameEnum[] roles = RoleNameEnum.values();
    private final FolderMovieType[] folderTypes = FolderMovieType.values();

    private final UserService userService;
    private final RoleService roleService;
    private final FolderService folderService;


    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    public void initRoles() {
        for (RoleNameEnum role : roles) {
            Role newRole = new Role();
            newRole.setName(role);
            roleService.save(newRole);
        }
    }

    @Order(2)
    @EventListener(ApplicationReadyEvent.class)
    public void initUsers() {
        Random random = new Random();
        int userNum = 1;
        for (int i = 0; i < 25; i++) {
            String email = "user" + userNum + "@mail.ru";
            String firstName = "Имя" + userNum;
            String lastName = "Фамилия" + userNum;
            String password = "password";
            LocalDate birthday = LocalDate.of(random.nextInt(41) + 1970, Month.JANUARY, 1)
                    .plusDays(random.nextInt(365))
                    .plusYears(random.nextInt(41));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName(RoleNameEnum.USER));
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(firstName);
            newUser.setLastName(lastName);
            newUser.setPassword(password);
            newUser.setBirthday(birthday);
            newUser.setRoles(roles);
            userService.save(newUser);

            initFolders(newUser.getEmail());

            userNum++;
        }

        // инициализация администратора
        String email = "admin@gmail.com";
        String firstName = "admin";
        String lastName = "admin";
        String password = "admin";
        LocalDate birthday = LocalDate.of(random.nextInt(41) + 1970, Month.JANUARY, 1)
                .plusDays(random.nextInt(365))
                .plusYears(random.nextInt(41));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.findByName(RoleNameEnum.USER));
        adminRoles.add(roleService.findByName(RoleNameEnum.ADMIN));
        User newAdmin = new User();
        newAdmin.setEmail(email);
        newAdmin.setName(firstName);
        newAdmin.setLastName(lastName);
        newAdmin.setPassword(password);
        newAdmin.setBirthday(birthday);
        newAdmin.setRoles(adminRoles);
        userService.save(newAdmin);

        initFolders(newAdmin.getEmail());

        // инициализация издателя
        email = "publicist@gmail.com";
        firstName = "publicist";
        lastName = "publicist";
        password = "publicist";
        birthday = LocalDate.of(random.nextInt(41) + 1970, Month.JANUARY, 1)
                .plusDays(random.nextInt(365))
                .plusYears(random.nextInt(41));
        Set<Role> publicistRoles = new HashSet<>();
        publicistRoles.add(roleService.findByName(RoleNameEnum.USER));
        publicistRoles.add(roleService.findByName(RoleNameEnum.PUBLICIST));
        User newPublicist = new User();
        newPublicist.setEmail(email);
        newPublicist.setName(firstName);
        newPublicist.setLastName(lastName);
        newPublicist.setPassword(password);
        newPublicist.setBirthday(birthday);
        newPublicist.setRoles(publicistRoles);
        userService.save(newPublicist);

        initFolders(newPublicist.getEmail());
    }

    private void initFolders(String email) {
        User user = userService.findByEmail(email);

        for (FolderMovieType folderType : folderTypes) {
            if (folderType != FolderMovieType.CUSTOM) {
                String name = folderType.getName();
                String description = "описание описание описание описание описание описание описание описание ";
                Privacy privacy = Privacy.PUBLIC;

                FolderMovie folder = new FolderMovie();
                folder.setName(name);
                folder.setDescription(description);
                folder.setPrivacy(privacy);
                folder.setUser(user);

                folderService.save(folder);
            }
        }
    }
}