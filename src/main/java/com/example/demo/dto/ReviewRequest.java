package com.example.demo.dto;

import com.example.demo.domain.RoomType;
import com.example.demo.domain.User;

import java.sql.Timestamp;

public record ReviewRequest(
        String message,
        String roomType
) {
}
