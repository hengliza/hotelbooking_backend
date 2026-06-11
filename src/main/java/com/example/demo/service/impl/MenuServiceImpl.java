package com.example.demo.service.impl;

import com.example.demo.domain.Category;
import com.example.demo.domain.Menu;
import com.example.demo.dto.MenuRequest;
import com.example.demo.dto.MenuResponse;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final MenuMapper menuMapper;

    @Override
    public MenuResponse create(MenuRequest request) {

        if(menuRepository.findByName(request.name()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This name already exists");
        }

        Category category = categoryRepository.findById(
                request.categoryId()
        ).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT,"Category not found")
        );

        Menu menu = menuMapper.toEntity(request, category);

        return menuMapper.toResponse(
                menuRepository.save(menu)
        );
    }

    @Override
    public List<MenuResponse> getAll() {
        return menuRepository.findAll()
                .stream()
                .map(menuMapper::toResponse)
                .toList();
    }

    @Override
    public MenuResponse getById(Integer id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NO_CONTENT,"Menu not found")
                );

        return menuMapper.toResponse(menu);
    }

    @Override
    public MenuResponse update(Integer id, MenuRequest request) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NO_CONTENT,"Menu not found")
                );

        Category category = categoryRepository.findById(
                request.categoryId()
        ).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT,"Category not found")
        );

        menu.setName(request.name());
        menu.setDescription(request.description());
        menu.setIngredients(request.ingredients());
        menu.setPrice(request.price());
        menu.setIsAvailable(request.isAvailable());
        menu.setCategory(category);

        return menuMapper.toResponse(
                menuRepository.save(menu)
        );
    }

    @Override
    public void delete(Integer id) {
        menuRepository.deleteById(id);
    }
}