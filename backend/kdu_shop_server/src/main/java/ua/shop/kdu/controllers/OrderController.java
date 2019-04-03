package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.Order;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.OrderService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Map<String, Long> createOrder(@Valid @RequestBody Order order) throws NotFoundException {
        return Collections.singletonMap("orderId",  orderService.createOrder(order));
    }

    @GetMapping
    public Page<Order> getUserOrders(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
        return orderService.getUserOrders(pageNumber, pageSize);
    }

    @PostMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable Long id) throws NotFoundException {
        orderService.cancelOrder(id);
    }

    @PostMapping("/confirm/{id}")
    public void confirmOrder(@PathVariable Long id) throws NotFoundException {
        orderService.confirmOrder(id);
    }

    @PostMapping("/shipped-out/{id}")
    public void markOrderAsShippedOut(@PathVariable Long id) throws NotFoundException {
        orderService.markAsShippedOut(id);
    }

    @GetMapping("/all")
    public Page<Order> getAllOrders(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
        return orderService.getAllOrders(pageNumber, pageSize);
    }
}
