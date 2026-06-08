package com.example.demo.init;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryInitialize {
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if (categoryRepository.count() == 0) {
            Category breakfast = new Category();
            breakfast.setName("Breakfast");

            Category lunch = new Category();
            lunch.setName("Lunch");

            Category dinner = new Category();
            dinner.setName("Dinner");

            Category fineDinning = new Category();
            fineDinning.setName("FineDinning");

            Category desserts = new Category();
            desserts.setName("Desserts");

            Category drinks = new Category();
            drinks.setName("Drinks");

            categoryRepository.saveAll(List.of(breakfast, lunch, dinner, fineDinning, desserts, drinks));
        }
    }
}
