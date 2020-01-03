package com.example.toolsserver.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private static CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }
}
