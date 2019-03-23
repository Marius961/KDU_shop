package ua.shop.kdu.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.shop.kdu.entities.Order;
import ua.shop.kdu.entities.OrderedItem;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.OrderRepo;
import ua.shop.kdu.repositories.OrderedItemRepo;
import ua.shop.kdu.repositories.ProductRepo;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private OrderedItemRepo orderedItemRepo;
    private OrderRepo orderRepo;
    private ProductRepo productRepo;
    private UserService userService;

    public OrderService(OrderedItemRepo orderedItemRepo, OrderRepo orderRepo, ProductRepo productRepo, UserService userService) {
        this.orderedItemRepo = orderedItemRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userService = userService;
    }

    @Transactional
    public void createOrder(Order order) throws NotFoundException {
        if (order.getOrderedItems() != null && !order.getOrderedItems().isEmpty()) {
            double orderTotalPrice = 0;
            order.setId(null);
            order.setConfirmed(false);
            order.setShippedOut(false);
            order.setDelivered(false);
            order.setCreationDate(new Date());
            for (OrderedItem item : order.getOrderedItems()) {
                Optional<Product> opProduct = productRepo.findById(item.getProduct().getId());
                if (opProduct.isPresent()) {
                    if (item.getQuantity() > 0) {
                        item.setId(null);
                        double itemTotalPrice = item.getQuantity() * opProduct.get().getPrice();
                        item.setTotalPrice(itemTotalPrice);
                        orderTotalPrice += itemTotalPrice;
                    } else throw new IllegalArgumentException("Quantity of item with id: "+ item.getId() +" must be more than 0");
                } else throw new NotFoundException("Cannot add product with id: " + item.getProduct().getId() + " to order, because it is not exist");
            }
            order.setTotalPrice(orderTotalPrice);
            order.setUser((User) userService.loadUserByUsername(getPrincipal().getName()));
            orderRepo.save(order);
        } else throw new NotFoundException("Cannot find order items in this order");
    }

    public Page<Order> getUserOrders(int page, int pageSize) {
        User currentUser = (User) userService.loadUserByUsername(getPrincipal().getName());
        return orderRepo.findAllByUser(currentUser, PageRequest.of(page, pageSize));
    }

    public void confirmOrder(Long orderId) {

    }

    public void markOrderAsDelivered(Long orderId) {

    }

    public void cancelOrder(Long orderId) {

    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
