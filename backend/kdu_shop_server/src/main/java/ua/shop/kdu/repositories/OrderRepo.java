package ua.shop.kdu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.shop.kdu.entities.Order;
import ua.shop.kdu.entities.User;

public interface OrderRepo extends PagingAndSortingRepository<Order, Long> {

    Page<Order> findAllByUser(User user, Pageable pageable);
}
