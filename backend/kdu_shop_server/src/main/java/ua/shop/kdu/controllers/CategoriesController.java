package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.CategoryService;

import javax.validation.Valid;
import java.util.Collections;
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
    public void addCategory(@Valid @RequestBody Category category) {
        categoryService.saveCategory(category);
    }

    @PostMapping("/check-name")
    public Map<String, Boolean> checkCategoryName(@RequestBody Map<String, String> categoryName) {
        return Collections.singletonMap("isExist", categoryService.isCategoryNameExist(categoryName.get("categoryName")));
    }

    @PostMapping("/check-url")
    public Map<String, Boolean> checkCategoryUrl(@RequestBody Map<String, String> categoryIrl) {
        return Collections.singletonMap("isExist", categoryService.isCategoryUrlExist(categoryIrl.get("categoryUrl")));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) throws NotFoundException {
        categoryService.deleteCategory(id);
    }

    @PutMapping
    public void updateCategory(@Valid @RequestBody Category category) throws NotFoundException {
        categoryService.updateCategory(category);
    }
}
