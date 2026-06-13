package com.example.demo.dto;

import java.sql.Timestamp;

public record ReceiptResponse(
        String bookingNumber,
        Timestamp issuedAt,
        ReservationResponse reservation,
        String status) {
}
