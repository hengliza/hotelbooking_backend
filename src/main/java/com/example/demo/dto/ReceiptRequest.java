package com.example.demo.dto;

import com.example.demo.domain.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.sql.Timestamp;

public record ReceiptRequest(
        Integer reservationId
) {
}
