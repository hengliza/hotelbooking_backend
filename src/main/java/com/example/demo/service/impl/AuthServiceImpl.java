package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // ---------------- REGISTER ----------------
    @Transactional
    @Override
    public UserResponse registerUser(UserRequest request) {

        String provider = request.authProvider().toUpperCase();

        if ("LOCAL".equals(provider)) {
            validateLocalRegister(request);

            User user = userMapper.toEntity(request);
            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
            user.setAuthProvider("LOCAL");

            return userMapper.toResponse(userRepository.save(user));
        }

        if ("GOOGLE".equals(provider)) {
            validateGoogleRegister(request);

            User user = userMapper.toEntity(request);
            user.setAuthProvider("GOOGLE");
            user.setPassword(null);

            return userMapper.toResponse(userRepository.save(user));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unknown Authentication Provider: " + provider);
    }

    // ---------------- LOGIN ----------------
    @Transactional
    @Override
    public AuthResponse loginUser(AuthRequest request) {

        String provider = request.authProvider().toUpperCase();

        if ("LOCAL".equals(provider)) {
            return loginLocal(request);
        }

        if ("GOOGLE".equals(provider)) {
            return loginGoogle(request);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Authentication Provider.");
    }

    // ---------------- LOCAL LOGIN ----------------
    private AuthResponse loginLocal(AuthRequest request) {

        User user = userRepository.findByPhoneNumber(request.phoneNumber())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid phone number or password.")
                );

        if (!"LOCAL".equals(user.getAuthProvider())) {
            throw new IllegalStateException(
                    "This account uses Google Sign-In. Please log in with Google."
            );
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid phone number or password.");
        }

        return buildAuthResponse(user);
    }

    // ---------------- GOOGLE LOGIN ----------------
    private AuthResponse loginGoogle(AuthRequest request) {

        Optional<User> existingUser =
                userRepository.findByEmail(request.email());

        User user;

        if (existingUser.isPresent()) {

            user = existingUser.get();

            if (!"GOOGLE".equals(user.getAuthProvider())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Email already linked to local account. Use phone login."
                );
            }

        } else {

            user = new User();
            user.setUsername(request.email().split("@")[0]);
            user.setEmail(request.email());
            user.setAuthProvider("GOOGLE");

            user = userRepository.save(user);
        }

        return buildAuthResponse(user);
    }

    // ---------------- JWT RESPONSE ----------------
    private AuthResponse buildAuthResponse(User user) {

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(
                token,
                userMapper.toResponse(user)
        );
    }

    // ---------------- VALIDATIONS ----------------
    private void validateLocalRegister(UserRequest request) {

        if (request.phoneNumber() == null || request.phoneNumber().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Phone number is required.");
        }

        if (request.password() == null || request.password().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password is required.");
        }

        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Phone number already registered.");
        }
    }

    private void validateGoogleRegister(UserRequest request) {

        if (request.email() == null || request.email().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Google email is required.");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Google account already registered.");
        }
    }
}