package com.markovets.password_manager.repository;

import com.markovets.password_manager.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    List<Password> findByUserId(Long userId);
}