package com.example.demo.dto;

public record RoomResponse(
        Integer id,
        String roomNumber,
        RoomTypeResponse roomType,
        String status
) {
}
