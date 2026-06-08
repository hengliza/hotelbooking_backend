package com.example.demo.dto;

public record RoomTypeResponse(
        String name,
        String description,
        Double price,
        Integer capacity,
        String image
) {
}
