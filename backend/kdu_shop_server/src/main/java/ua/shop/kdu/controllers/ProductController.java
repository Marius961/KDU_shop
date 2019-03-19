package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.ProductService;

import java.util.List;

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

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return productService.findAllProducts(page, size);
    }

    @GetMapping("/category/{url}")
    public Page<Product> getProductsByCategory(
            @PathVariable String url,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "colors", required = false)List<String> colors,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") int minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "0") int maxPrice
    ) throws NotFoundException {

        return productService.getProductsByCategoryUrl(
                url, page, size, colors, maxPrice, minPrice);
    }

    @PostMapping
    public void addProduct(@RequestPart(name = "image") MultipartFile file, @RequestPart(name = "product") Product product) throws Exception {
        System.out.println("Product name:  " + product.getName());
        productService.addProduct(product, file);
    }
}
