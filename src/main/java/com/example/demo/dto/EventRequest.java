package com.example.demo.dto;

import jakarta.persistence.Column;

import java.sql.Date;
import java.sql.Timestamp;

public record EventRequest(
        String title,
        String description,
        Date startDate,
        Date endDate
) {
}
