package com.example.toolsserver.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private static CategoryService categoryService;

    @PostMapping("categorys")
    public Category createCategory(@RequestBody Category request) {
        return categoryService.createCategory(request);
    }
}
