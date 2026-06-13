package com.example.demo.dto;


public record ReviewRequest(
        String username,
        String roomTypeName,
        String message
) {
}