package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.OrderedItem;
import ua.shop.kdu.entities.Product;

public interface OrderedItemRepo extends CrudRepository<OrderedItem, Long> {

    boolean existsByProduct(Product product);

}
