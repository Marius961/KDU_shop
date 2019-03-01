package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.CategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{url}")
    public Category getCategoryByUrl(@PathVariable String url) throws NotFoundException {
        return categoryService.getCategoryByUrl(url);
    }

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }
}
