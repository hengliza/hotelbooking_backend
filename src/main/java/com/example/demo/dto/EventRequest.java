package com.example.demo.dto;

import jakarta.persistence.Column;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public record EventRequest(
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
