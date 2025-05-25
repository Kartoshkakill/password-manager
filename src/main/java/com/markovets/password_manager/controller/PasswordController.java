package com.markovets.password_manager.controller;

import com.markovets.password_manager.dto.PasswordDTO;
import com.markovets.password_manager.model.Password;
import com.markovets.password_manager.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
@SecurityRequirement(name = "BearerAuth")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/{userId}")
    @Operation(summary = "Add a password", description = "Adds a new password for the specified user")
    @ApiResponse(responseCode = "200", description = "Password added successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Password> addPassword(@PathVariable Long userId, @RequestBody PasswordDTO passwordDTO) {
        Password password = passwordService.addPassword(userId, passwordDTO);
        return ResponseEntity.ok(password);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user passwords", description = "Retrieves all passwords for the specified user")
    @ApiResponse(responseCode = "200", description = "Passwords retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<List<Password>> getUserPasswords(@PathVariable Long userId) {
        List<Password> passwords = passwordService.getUserPasswords(userId);
        return ResponseEntity.ok(passwords);
    }


}