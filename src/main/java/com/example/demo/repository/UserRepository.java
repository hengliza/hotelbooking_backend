package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail (String email);
    Optional<User> findByPhoneNumber (String phoneNumber);
    boolean existsByEmail (String email);
    boolean existsByPhoneNumber (String phoneNumber);
}
