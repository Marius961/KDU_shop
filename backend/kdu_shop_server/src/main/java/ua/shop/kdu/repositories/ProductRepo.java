package ua.shop.kdu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.entities.Product;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findByCategory(Category category, Pageable pageable);
}
