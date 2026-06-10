package com.example.demo.controller;

import com.example.demo.dto.MenuRequest;
import com.example.demo.dto.MenuResponse;
import com.example.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public MenuResponse create(@RequestBody MenuRequest request) {
        return menuService.create(request);
    }

    @GetMapping
    public List<MenuResponse> getAll() {
        return menuService.getAll();
    }

    @GetMapping("/{id}")
    public MenuResponse getById(@PathVariable Integer id) {
        return menuService.getById(id);
    }

    @PatchMapping("/{id}")
    public MenuResponse update(@PathVariable Integer id, @RequestBody MenuRequest request) {
        return menuService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        menuService.delete(id);
    }
}