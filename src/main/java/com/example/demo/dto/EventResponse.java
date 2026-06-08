package com.example.demo.dto;

import java.sql.Date;
import java.sql.Timestamp;

public record EventResponse(
        String title,
        String description,
        Date startDate,
        Date endDate,
        Timestamp createdAt
){
}
