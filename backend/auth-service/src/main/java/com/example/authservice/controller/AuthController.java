package com.example.authservice.controller;

import com.example.authservice.entity.AuthUser;
import com.example.authservice.repository.AuthUserRepository;
import com.example.authservice.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserRepository repository;
    private final JwtUtil jwtUtil;

    public AuthController(AuthUserRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public AuthUser register(@RequestBody AuthUser user) {
        return repository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthUser user) {
        return repository.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getUsername()))
                .orElse("Invalid credentials");
    }
}
