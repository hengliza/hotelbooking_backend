package com.example.demo.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public record EventResponse(
        Integer id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Timestamp createdAt
){
}
