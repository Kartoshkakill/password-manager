package com.markovets.password_manager.dto;

public class PasswordDTO {
    private String website;
    private String username;
    private String password;

    public PasswordDTO() {
    }

    public PasswordDTO(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}