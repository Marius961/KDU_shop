package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.entities.Product;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    Iterable<Product> findByCategoryId(long categoryId);
}
