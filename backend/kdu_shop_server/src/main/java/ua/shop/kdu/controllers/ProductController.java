package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) throws NotFoundException {
        return productService.getProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) throws NotFoundException {
        productService.addProduct(product);
    }
}
