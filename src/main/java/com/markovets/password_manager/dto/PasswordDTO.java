package com.markovets.password_manager.dto;

import lombok.Data;

@Data
public class PasswordDTO {
    private String website;
    private String username;
    private String password;
}