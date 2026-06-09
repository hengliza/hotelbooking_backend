package com.example.demo.dto;

import com.example.demo.domain.RoomType;

public record RoomRequest(
        String roomNumber,
        Integer roomTypeId,
        String status
) {
}
