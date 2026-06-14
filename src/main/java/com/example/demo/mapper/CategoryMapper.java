package com.example.demo.mapper;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        if (category == null) return null;
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category toEntity(CategoryRequest request) {
        if (request == null) return null;
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        return category;
    }
}