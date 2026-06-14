package com.example.demo.dto;

import com.example.demo.domain.Menu;

public record CategoryResponse (
        Integer id,
        String name,
        String description
){
}
