package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.Order;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder(@Valid @RequestBody Order order) throws NotFoundException {
        orderService.createOrder(order);
    }

    @GetMapping
    public Page<Order> getUserOrders(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
        return orderService.getUserOrders(pageNumber, pageSize);
    }
}
