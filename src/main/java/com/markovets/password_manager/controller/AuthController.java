package com.markovets.password_manager.controller;

import com.markovets.password_manager.dto.AuthRequest;
import com.markovets.password_manager.model.User;
import com.markovets.password_manager.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private final String JWT_SECRET = "secret-key-1234567890secret-key-1234567890";
    private final Key JWT_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    private final long JWT_EXPIRATION = 86400000; // 24 hours

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticates user and returns JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String jwt = Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(JWT_KEY)
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("role", user.getRole());
        return ResponseEntity.ok(response);
    }
}