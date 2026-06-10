package com.example.demo.dto;


public record ReviewRequest(
        Integer reviewId,
        Integer userId,
        Integer roomTypeId,
        String message
) {
}