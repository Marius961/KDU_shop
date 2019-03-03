package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.Category;

import java.util.Optional;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    Optional<Category> findFirstByCategoryUrl(String categoryUrl);

    Optional<Category> findFirstByCategoryName(String categoryName);
}
