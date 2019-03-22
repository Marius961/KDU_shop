package ua.shop.kdu.services;

import org.springframework.stereotype.Service;
import ua.shop.kdu.entities.Order;
import ua.shop.kdu.repositories.OrderedItemRepo;

@Service
public class OrderService {

    private OrderedItemRepo orderedItemRepo;
    private Order order;

    public OrderService(OrderedItemRepo orderedItemRepo, Order order) {
        this.orderedItemRepo = orderedItemRepo;
        this.order = order;
    }

    public void createOrder(Order order) {

    }

    public void confirmOrder(Long orderId) {

    }

    public void markOrderAsDelivered(Long orderId) {

    }

    public void cancelOrder(Long orderId) {

    }
}
