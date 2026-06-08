package com.example.demo.dto;

public record UserRequest(
        String username,
        String email,
        String phoneNumber,
        String authProvider,
        String password
) {
}
