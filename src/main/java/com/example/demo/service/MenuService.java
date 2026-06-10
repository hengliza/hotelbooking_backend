package com.example.demo.service;

import com.example.demo.dto.MenuRequest;
import com.example.demo.dto.MenuResponse;

import java.util.List;

public interface MenuService {
    MenuResponse create(MenuRequest menuRequest);
    List<MenuResponse> getAll();
    MenuResponse getById(Integer menuId);
    MenuResponse update(Integer id,MenuRequest menuRequest);
    void delete(Integer menuId);
}
