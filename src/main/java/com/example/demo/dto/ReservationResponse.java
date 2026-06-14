package com.example.demo.dto;

import com.example.demo.domain.Receipt;
import com.example.demo.domain.Room;

import java.sql.Timestamp;

public record ReservationResponse(
        Integer id,
        Timestamp checkinDate,
        Timestamp checkoutDate,
        Integer adultAmount,
        Integer childAmount,
        String status,
        Timestamp createdDate,
        Double totalPrice,
        RoomResponse room
) {
}
