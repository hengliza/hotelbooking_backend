package com.example.demo.mapper;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;

public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        if (category == null) return null;
        return new CategoryResponse(
                category.getName(),
                category.getDescription()
        );
    }

    public static Category toEntity(CategoryRequest request) {
        if (request == null) return null;
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        return category;
    }
}