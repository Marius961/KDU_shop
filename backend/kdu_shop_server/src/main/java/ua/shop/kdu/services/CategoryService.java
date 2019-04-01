package ua.shop.kdu.services;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.CategoryRepo;
import ua.shop.kdu.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;
    private ProductRepo productRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    public Category getCategoryById(long id) throws NotFoundException {
        Optional category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return (Category) category.get();
        }
        throw new NotFoundException("Category not found");
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void saveCategory(Category category) {
        category.setId(null);
        categoryRepo.save(category);
    }

    public void updateCategory(Category category) throws NotFoundException {
        if (category.getId() != null) {
            categoryRepo.save(category);
        } else throw new NotFoundException("Cannot update category with id null");
    }

    public void deleteCategory(Long categoryId) throws NotFoundException {
        System.out.println(categoryId + "  category id =============");
        Optional<Category> opCategory = categoryRepo.findById(categoryId);
        if (opCategory.isPresent()) {
            if (productRepo.existsByCategory(opCategory.get())) {
                throw new IllegalArgumentException("Unable to delete category with products, first remove all products.");
            } else categoryRepo.deleteById(categoryId);
        } else throw new NotFoundException("Cannot find category with id: " + categoryId);
    }

    public Category getCategoryByUrl(String url) throws NotFoundException {
        Optional category = categoryRepo.findFirstByCategoryUrl(url);
        if (category.isPresent()){
            return (Category) category.get();
        }
        throw new NotFoundException("Category not found");
    }

    public boolean isCategoryNameExist(String name) {
        Optional oCategory = categoryRepo.findFirstByCategoryName(name);
        return oCategory.isPresent();
    }

    public boolean isCategoryUrlExist(String url) {
        Optional oCategory = categoryRepo.findFirstByCategoryUrl(url);
        return oCategory.isPresent();
    }
}
