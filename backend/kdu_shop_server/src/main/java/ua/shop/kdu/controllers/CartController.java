package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.CartItem;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.CartService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Iterable<CartItem> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @PostMapping
    public void addCartItem(@Valid @RequestBody CartItem cartItem) throws NotFoundException {
        cartService.addItemToCart(cartItem);
    }

    @DeleteMapping("/{itemId}")
    public void deleteCartItem(@PathVariable Long itemId) throws NotFoundException {
        cartService.removeItemFromCart(itemId);
    }

    @PutMapping("/{itemId}/{quantity}")
    public void updateItemQuantity(@PathVariable Long itemId, @PathVariable int quantity) throws NotFoundException {
        cartService.updateItemQuantity(itemId, quantity);
    }
}
