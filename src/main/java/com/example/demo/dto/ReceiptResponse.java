package com.example.demo.dto;

import com.example.demo.domain.Reservation;

import java.sql.Timestamp;

public record ReceiptResponse(
        String bookingNumber,
        Timestamp issuedAt,
        Integer reservationId
) {
}
