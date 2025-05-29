package com.markovets.password_manager.service;

import com.markovets.password_manager.dto.PasswordDTO;
import com.markovets.password_manager.model.Password;
import com.markovets.password_manager.model.User;
import com.markovets.password_manager.repository.PasswordRepository;
import com.markovets.password_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private UserRepository userRepository;

    public Password addPassword(Long userId, PasswordDTO passwordDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Password password = new Password(user, passwordDTO.getWebsite(), passwordDTO.getUsername(), passwordDTO.getPassword());
        return passwordRepository.save(password);
    }

    public List<Password> getUserPasswords(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return passwordRepository.findByUser(user);
    }

    public Password updatePassword(Long passwordId, PasswordDTO passwordDTO) {
        Password password = passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));
        password.setWebsite(passwordDTO.getWebsite());
        password.setUsername(passwordDTO.getUsername());
        password.setPassword(passwordDTO.getPassword());
        return passwordRepository.save(password);
    }

    public void deletePassword(Long passwordId) {
        Password password = passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));
        passwordRepository.delete(password);
    }
}