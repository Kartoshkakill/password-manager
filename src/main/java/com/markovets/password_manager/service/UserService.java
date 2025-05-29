package com.markovets.password_manager.service;

import com.markovets.password_manager.dto.UserDTO;
import com.markovets.password_manager.model.User;
import com.markovets.password_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public User createAdmin(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("ROLE_ADMIN");

        return userRepository.save(user);
    }
}