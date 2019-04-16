package ua.shop.kdu.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.shop.kdu.entities.CartItem;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.CartItemRepo;
import ua.shop.kdu.repositories.ProductRepo;

import java.security.Principal;
import java.util.Optional;

@Service
public class CartService {

    private CartItemRepo cartItemRepo;
    private ProductRepo productRepo;
    private UserService userService;

    public CartService(CartItemRepo cartItemRepo, ProductRepo productRepo, UserService userService) {
        this.cartItemRepo = cartItemRepo;
        this.productRepo = productRepo;
        this.userService = userService;
    }

    public void addItemToCart(CartItem cartItem) throws NotFoundException {
        Long productId = cartItem.getProduct().getId();
        Optional<Product> existedProduct = productRepo.findById(productId);
        if (existedProduct.isPresent()) {
            User currentUser = (User) userService.loadUserByUsername(getPrincipal().getName());
            Optional<CartItem> alreadyExistedItem = cartItemRepo.findByProductAndSizeAndUser(existedProduct.get(),cartItem.getSize(), currentUser);
            if (alreadyExistedItem.isPresent()) {
                CartItem updatedItem = alreadyExistedItem.get();
                updatedItem.setQuantity(updatedItem.getQuantity() + cartItem.getQuantity());
                cartItemRepo.save(updatedItem);
            } else if (currentUser != null) {
                cartItem.setUser(currentUser);
                cartItemRepo.save(cartItem);
            } else throw new UsernameNotFoundException("Cannot find user with username: " + getPrincipal().getName());
        } else throw new NotFoundException("Cannot add product to cart. Product with id: " + productId + " not exist.");
    }

    public void removeItemFromCart(Long itemId) throws NotFoundException {
        User currentUser = (User) userService.loadUserByUsername(getPrincipal().getName());
        boolean isItemExist = cartItemRepo.existsByIdAndUser(itemId, currentUser);
        if (isItemExist) {
            cartItemRepo.deleteById(itemId);
        } else throw new NotFoundException("Cannot find cart item with id: " + itemId);
    }

    public void updateItemQuantity(Long itemId, int quantity) throws NotFoundException {
        User currentUser = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<CartItem> opCartItem = cartItemRepo.findByIdAndUser(itemId, currentUser);
        if (opCartItem.isPresent()) {
            CartItem cartItem = opCartItem.get();
            cartItem.setQuantity(quantity);
            cartItemRepo.save(cartItem);
        } else throw new NotFoundException("Cannot find cart item with id: " + itemId);
    }

    public Iterable<CartItem> getAllCartItems() {
        User currentUser = (User) userService.loadUserByUsername(getPrincipal().getName());
        return cartItemRepo.findAllByUser(currentUser);
    }

    public void clearCart() {
        cartItemRepo.deleteAll();
    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
