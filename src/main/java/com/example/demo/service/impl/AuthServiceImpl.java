package com.example.demo.service.impl;

import com.example.demo.domain.RefreshToken;
import com.example.demo.domain.User;
import com.example.demo.dto.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RefreshTokenRepository;
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

@RequiredArgsConstructor
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshServiceImpl refreshTokenService;

    @Override
    public AuthResponse registerUser(UserRequest request) {

        String provider = request.authProvider().toUpperCase();

        User user;

        switch (provider) {

            case "LOCAL" -> {

                validateLocalRegister(request);

                user = userMapper.toEntity(request);

                user.setPassword(
                        passwordEncoder.encode(
                                user.getPassword()
                        )
                );

                user.setAuthProvider("LOCAL");

                user = userRepository.save(user);

                return buildAuthResponse(user);
            }

            case "GOOGLE" -> {

                validateGoogleRegister(request);

                user = userMapper.toEntity(request);

                user.setAuthProvider("GOOGLE");
                user.setPassword(null);

                user = userRepository.save(user);

                return buildAuthResponse(user);
            }

            default -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unknown Authentication Provider: " + provider
            );
        }
    }

    @Override
    public AuthResponse loginUser(AuthRequest request) {

        String provider = request.authProvider().toUpperCase();

        return switch (provider) {

            case "LOCAL" -> loginLocal(request);

            case "GOOGLE" -> loginGoogle(request);

            default -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid Authentication Provider."
            );
        };
    }

    private AuthResponse loginLocal(AuthRequest request) {

        User user = userRepository
                .findByPhoneNumber(request.phoneNumber())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Invalid phone number or password."
                        )
                );

        if (!"LOCAL".equals(user.getAuthProvider())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This account uses Google Sign-In."
            );
        }

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid phone number or password."
            );
        }

        return buildAuthResponse(user);
    }

    private AuthResponse loginGoogle(AuthRequest request) {

        User user = userRepository
                .findByEmail(request.email())
                .orElseGet(() -> {

                    User newUser = new User();

                    newUser.setUsername(
                            request.email().split("@")[0]
                    );

                    newUser.setEmail(request.email());
                    newUser.setAuthProvider("GOOGLE");

                    return userRepository.save(newUser);
                });

        if (!"GOOGLE".equals(user.getAuthProvider())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already linked to local account."
            );
        }

        return buildAuthResponse(user);
    }

    @Override
    public AuthResponse refreshToken(RefreshRequest request) {
        RefreshToken refreshToken =
                refreshTokenService.validateRefreshToken(
                        request.refreshToken()
                );

        User user = refreshToken.getUser();

        String accessToken =
                jwtService.generateToken(
                        user.getUsername()
                );

        return new AuthResponse(
                accessToken,
                request.refreshToken(),
                userMapper.toResponse(user)
        );
    }

    @Override
    public void logout(String username) {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );

        refreshTokenService.deleteByUser(user);
    }

    private AuthResponse buildAuthResponse(User user) {

        String accessToken =
                jwtService.generateToken(
                        user.getUsername()
                );

        String refreshToken =
                refreshTokenService.createRefreshToken(
                        user
                );

        return new AuthResponse(
                accessToken,
                refreshToken,
                userMapper.toResponse(user)
        );
    }

    private void validateLocalRegister(
            UserRequest request
    ) {

        if (request.phoneNumber() == null
                || request.phoneNumber().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Phone number is required."
            );
        }

        if (request.password() == null
                || request.password().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password is required."
            );
        }

        if (userRepository.existsByPhoneNumber(
                request.phoneNumber()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number already registered."
            );
        }
    }

    private void validateGoogleRegister(
            UserRequest request
    ) {

        if (request.email() == null
                || request.email().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Google email is required."
            );
        }

        if (userRepository.existsByEmail(
                request.email()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Google account already registered."
            );
        }
    }
}
