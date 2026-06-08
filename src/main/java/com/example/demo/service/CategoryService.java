package com.example.demo.service;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse findById(Integer id);
    CategoryResponse save(CategoryRequest categoryRequest);
    CategoryResponse update(CategoryRequest categoryRequest);
    void deleteById(Integer id);
}
