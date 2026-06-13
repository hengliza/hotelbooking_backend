package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        String email,

        @Size(max = 15, message = "Phone number cannot exceed 15 characters")
        String phoneNumber,

        String password,

        @NotBlank(message = "Authentication provider is required")
        String authProvider // 'LOCAL' or 'GOOGLE'
) {}
