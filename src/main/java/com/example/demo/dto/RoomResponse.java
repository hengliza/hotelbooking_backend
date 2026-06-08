package com.example.demo.dto;

public record RoomResponse(
        String roomNumber,
        RoomTypeResponse roomType,
        String status
) {
}
