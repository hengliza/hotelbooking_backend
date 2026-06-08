package com.example.demo.dto;

import com.example.demo.domain.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record MenuRequest(
        String name,
        String description,
        String ingredients,
        Double price,
        Boolean isAvailable,
        String category
) {
}
