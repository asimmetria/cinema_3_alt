package com.kata.cinema.base.util;

import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.security.config.JwtService;
import com.kata.cinema.base.security.token.Token;
import com.kata.cinema.base.security.token.TokenRepository;
import com.kata.cinema.base.security.token.TokenType;
import com.kata.cinema.base.service.entity.RoleService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Component
public class JwtUtil {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;


    @Autowired
    public JwtUtil(PasswordEncoder passwordEncoder,
                   RoleService roleService,
                   UserRepository userRepository,
                   JwtService jwtService,
                   TokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
    }

    @PreDestroy
    public void destroy() {
        roleService.deleteAll();
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }


    /**
     * Генерирует JWT токен для тестового пользователя
     *
     * @return JWT токен
     */
    public String generateToken() {
        var testUser = initTestUser();
        var jwtToken = jwtService.generateToken(testUser);
        if (tokenRepository.findByToken(jwtToken).isPresent()) {
            return jwtToken;
        }
        var token = Token.builder()
                .user(testUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        return jwtToken;
    }

    private User initTestUser() {
        var existingUser = userRepository.findByEmail("test@example.com");
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(RoleNameEnum.USER));
        roles.add(roleService.findByName(RoleNameEnum.ADMIN));
        roles.add(roleService.findByName(RoleNameEnum.PUBLICIST));
        var user = User
                .builder()
                .name("test")
                .lastName("test")
                .email("test@example.com")
                .password(passwordEncoder.encode("test"))
                .birthday(LocalDate.of(2000, 1, 1))
                .roles(roles)
                .enable(true)
                .build();
        userRepository.save(user);
        return user;
    }

    private void initRoles() {
        final RoleNameEnum[] roles = RoleNameEnum.values();
        for (RoleNameEnum role : roles) {
            Role newRole = new Role();
            newRole.setName(role);
            roleService.save(newRole);
        }
    }

}
