package ua.shop.kdu.services;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.CategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
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

    public boolean saveCategory(Category category) {
        try {
            categoryRepo.save(category);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public Category getCategoryByUrl(String url) throws NotFoundException {
        Optional category = categoryRepo.findFirstByCategoryUrl(url);
        if (category.isPresent()){
            return (Category) category.get();
        }
        throw new NotFoundException("Category not found");
    }
}
