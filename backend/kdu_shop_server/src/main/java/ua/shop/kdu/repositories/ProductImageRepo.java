package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.ProductImage;

public interface ProductImageRepo extends CrudRepository<ProductImage, Long> {
}
