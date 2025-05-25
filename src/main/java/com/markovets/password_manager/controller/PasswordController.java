package com.markovets.password_manager.controller;

import com.markovets.password_manager.dto.PasswordDTO;
import com.markovets.password_manager.model.Password;
import com.markovets.password_manager.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/{userId}")
    public ResponseEntity<Password> addPassword(@PathVariable Long userId, @RequestBody PasswordDTO passwordDTO) {
        Password password = passwordService.addPassword(userId, passwordDTO);
        return ResponseEntity.ok(password);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Password>> getUserPasswords(@PathVariable Long userId) {
        List<Password> passwords = passwordService.getUserPasswords(userId);
        return ResponseEntity.ok(passwords);
    }
}