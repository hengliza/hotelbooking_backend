package com.example.demo.service;

import com.example.demo.dto.*;

public interface AuthService {
    AuthResponse registerUser(UserRequest userRequest);
    AuthResponse loginUser(AuthRequest authRequest);
    AuthResponse refreshToken(RefreshRequest refreshRequest);
    void logout(String username);
}
