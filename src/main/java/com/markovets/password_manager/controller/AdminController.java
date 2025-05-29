package com.markovets.password_manager.controller;

import com.markovets.password_manager.dto.UserDTO;
import com.markovets.password_manager.model.User;
import com.markovets.password_manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "BearerAuth")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create admin user", description = "Creates a new administrator")
    @ApiResponse(responseCode = "200", description = "Admin created successfully")
    public ResponseEntity<User> createAdmin(@RequestBody UserDTO userDTO) {
        User admin = userService.createAdmin(userDTO);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/test")
    @Operation(summary = "Test admin access", description = "Returns a greeting if accessed by admin")
    @ApiResponse(responseCode = "200", description = "Access granted")
    public String testAdmin() {
        return "Hello, Admin!";
    }
}
