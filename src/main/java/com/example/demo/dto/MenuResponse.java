package com.example.demo.dto;

import com.example.demo.domain.Category;

public record MenuResponse (
        String name,
        String description,
        String ingredients,
        Double price,
        Boolean isAvailable,
        CategoryResponse category
){
}
