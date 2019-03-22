package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.OrderedItem;

public interface OrderedItemRepo extends CrudRepository<OrderedItem, Long> {
}
