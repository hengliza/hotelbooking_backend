package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

public interface AuthService {
    UserResponse registerUser(UserRequest userRequest);
    AuthResponse loginUser(AuthRequest authRequest);
}
