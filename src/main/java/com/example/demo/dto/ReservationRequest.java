package com.example.demo.dto;

import com.example.demo.domain.Room;

import java.sql.Timestamp;

public record ReservationRequest(
    Timestamp checkinDate,
    Timestamp checkoutDate,
    Integer adultAmount,
    Integer childAmount,
    String roomNumber
) {
}
