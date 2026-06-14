package com.example.demo.mapper;

import com.example.demo.domain.Category;
import com.example.demo.domain.Menu;
import com.example.demo.dto.MenuRequest;
import com.example.demo.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {
    private final CategoryMapper categoryMapper;

    public MenuResponse toResponse(Menu menu) {
        if (menu == null) return null;
        return new MenuResponse(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getIngredients(),
                menu.getPrice(),
                menu.getIsAvailable(),
                categoryMapper.toResponse(menu.getCategory())
        );
    }

    public Menu toEntity(MenuRequest request, Category category) {
        if (request == null) return null;
        Menu menu = new Menu();
        menu.setName(request.name());
        menu.setDescription(request.description());
        menu.setIngredients(request.ingredients());
        menu.setPrice(request.price());
        menu.setIsAvailable(request.isAvailable());
        menu.setCategory(category); // Linked category fetched from DB by name/id
        return menu;
    }
}