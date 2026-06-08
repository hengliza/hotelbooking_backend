package com.example.demo.service;

import com.example.demo.dto.MenuRequest;
import com.example.demo.dto.MenuResponse;

import java.util.List;

public interface MenuService {
    List<MenuResponse> getAllMenu();
    MenuResponse getMenuById(Integer menuId);
    MenuResponse getMenuByName(String menuName);
    MenuResponse addMenu(MenuRequest menuRequest);
    MenuResponse updateMenu(MenuRequest menuRequest);
    void deleteMenu(Integer menuId);
}
