package com.example.demo.service;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);
    List<CategoryResponse> getAll();
    CategoryResponse getById(Integer id);
    CategoryResponse update(Integer id, CategoryRequest categoryRequest);
    void delete(Integer id);
}
