package com.markovets.password_manager.service;

import com.markovets.password_manager.dto.PasswordDTO;
import com.markovets.password_manager.model.Password;
import com.markovets.password_manager.model.User;
import com.markovets.password_manager.repository.PasswordRepository;
import com.markovets.password_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Password addPassword(Long userId, PasswordDTO passwordDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Password password = new Password();
        password.setUser(user);
        password.setWebsite(passwordDTO.getWebsite());
        password.setUsername(passwordDTO.getUsername());
        password.setEncryptedPassword(passwordEncoder.encode(passwordDTO.getPassword()));

        return passwordRepository.save(password);
    }

    public List<Password> getUserPasswords(Long userId) {
        return passwordRepository.findByUserId(userId);
    }
}