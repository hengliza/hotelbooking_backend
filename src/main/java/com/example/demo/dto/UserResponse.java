package com.example.demo.dto;

public record UserResponse (
        Integer id,
        String username,
        String email,
        String phoneNumber
){
}
