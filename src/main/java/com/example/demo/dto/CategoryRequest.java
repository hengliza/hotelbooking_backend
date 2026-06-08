package com.example.demo.dto;

import com.example.demo.domain.Menu;

public record CategoryRequest(
        String name,
        String description
) {
}
