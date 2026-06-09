package com.example.demo.mapper;

import com.example.demo.domain.Receipt;
import com.example.demo.domain.Reservation;
import com.example.demo.dto.ReceiptRequest;
import com.example.demo.dto.ReceiptResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class ReceiptMapper {

    public ReceiptResponse toResponse(Receipt receipt) {
        if (receipt == null) return null;
        return new ReceiptResponse(
                receipt.getBookingNumber(),
                receipt.getIssuedAt(),
                receipt.getReservation() != null ? receipt.getReservation().getId().intValue() : null
        );
    }

    public Receipt toEntity(ReceiptRequest request, Reservation reservation) {
        if (request == null) return null;
        Receipt receipt = new Receipt();
        receipt.setReservation(reservation);
        receipt.setBookingNumber("BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        receipt.setIssuedAt(new Timestamp(System.currentTimeMillis()));
        return receipt;
    }
}