package ua.shop.kdu.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.OrderedItem;
import ua.shop.kdu.entities.Product;

import java.util.List;

public interface OrderedItemRepo extends CrudRepository<OrderedItem, Long> {

    boolean existsByProduct(Product product);

    @Query("SELECT o.product FROM OrderedItem o group by o.product ORDER BY count(o.product) desc")
    List<Product> getPopularProducts(Pageable pageable);
}
