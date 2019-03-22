package ua.shop.kdu.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.shop.kdu.entities.Order;

public interface OrderRepo extends PagingAndSortingRepository<Order, Long> {
}
