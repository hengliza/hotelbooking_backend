package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        String provider = userRequest.authProvider().toUpperCase();

        if ("LOCAL".equals(provider)) {
            // Local Validation
            if (userRequest.phoneNumber() == null || userRequest.phoneNumber().isBlank()) {
                throw new IllegalArgumentException("Phone number is required for local registration.");
            }
            if (userRequest.password() == null || userRequest.password().isBlank()) {
                throw new IllegalArgumentException("Password is required for local registration.");
            }
            if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())) {
                throw new IllegalStateException("This phone number is already registered.");
            }

            User user = userMapper.toEntity(userRequest);
            user.setAuthProvider("LOCAL");

            return userMapper.toResponse(userRepository.save(user));

        } else if ("GOOGLE".equals(provider)) {
            // Google Validation
            if (userRequest.email() == null || userRequest.email().isBlank()) {
                throw new IllegalArgumentException("Google Email token payload is missing.");
            }
            if (userRepository.existsByEmail(userRequest.email())) {
                throw new IllegalStateException("This Google account is already registered.");
            }

            User user = userMapper.toEntity(userRequest);
            user.setAuthProvider("GOOGLE");
            user.setPassword(null);

            return userMapper.toResponse(userRepository.save(user));
        }

        throw new IllegalArgumentException("Unknown Authentication Provider: " + provider);
    }

    @Transactional
    @Override
    public UserResponse loginUser(AuthRequest authRequest) {
        String provider = authRequest.authProvider().toUpperCase();

        if ("LOCAL".equals(provider)) {
            // 1. Find user by phone number
            User user = userRepository.findByPhoneNumber(authRequest.phoneNumber())
                    .orElseThrow(() -> new RuntimeException("Invalid phone number or password."));

            // 2. Safeguard: Block Google accounts from trying to guess passwords
            if (!"LOCAL".equals(user.getAuthProvider())) {
                throw new IllegalStateException("This account uses Google Sign-In. Please log in with Google.");
            }

            // 3. Verify password
            if (!authRequest.password().equals(user.getPassword())) {
                throw new RuntimeException("Invalid phone number or password.");
            }

            return userMapper.toResponse(user);

        } else if ("GOOGLE".equals(provider)) {
            Optional<User> existingUser = userRepository.findByEmail(authRequest.email());

            if (existingUser.isPresent()) {
                User user = existingUser.get();
                if (!"GOOGLE".equals(user.getAuthProvider())) {
                    throw new IllegalStateException("Email already linked to a standard profile. Log in via form.");
                }
                return userMapper.toResponse(user);
            } else {
                User newUser = new User();
                newUser.setUsername(authRequest.email().split("@")[0]);
                newUser.setEmail(authRequest.email());
                newUser.setAuthProvider("GOOGLE");

                return userMapper.toResponse(userRepository.save(newUser));
            }
        }

        throw new IllegalArgumentException("Invalid Authentication Provider.");
    }
}