package ua.shop.kdu.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.entities.Product;

import java.util.List;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT DISTINCT p.color FROM Product p WHERE p.color IS NOT NULL")
    List<String> findAllColors();

    boolean existsByNameAndColor(String name, String color);

    boolean existsByCategory(Category category);
}
