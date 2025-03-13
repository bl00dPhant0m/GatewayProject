package ru.bl00dphant0m.securityservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.bl00dphant0m.securityservice.configuration.CustomUserPrincipalService;
import ru.bl00dphant0m.securityservice.model.LoginRequestDTO;
import ru.bl00dphant0m.securityservice.model.User;
import ru.bl00dphant0m.securityservice.service.JwtService;
import ru.bl00dphant0m.securityservice.service.UserService;


import java.util.Set;
import java.util.stream.Collectors;

// register - принимает юзера и отправляет что юзер зарегистрирован и закодировать пароль
//login - принимает логин реквест дто и возвращать токен и статус авторизирован
@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class AuthController {
    private final CustomUserPrincipalService customUserPrincipalService;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<User> getUsers(@RequestParam long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody LoginRequestDTO loginRequestDTO) {
        User userByUsername = userService.findUserByUsername(loginRequestDTO.getUsername());

        if (passwordEncoder.matches(loginRequestDTO.getPassword(), userByUsername.getPassword())) {
            String username = loginRequestDTO.getUsername();
            UserDetails userDetails = customUserPrincipalService.loadUserByUsername(username);

            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());


            return ResponseEntity.ok(jwtService.generateToken(username, roles));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неправильный пароль");

    }
}
