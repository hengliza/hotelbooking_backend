package com.example.demo.dto;

import jakarta.persistence.Column;

public record RoomTypeRequest(
        String name,
        String description,
        Double price,
        Integer capacity,
        String image
) {
}
