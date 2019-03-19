package ua.shop.kdu.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.shop.kdu.entities.Product;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
