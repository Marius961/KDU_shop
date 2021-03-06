package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.ProductService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public Page<Product> getAllProducts(@RequestParam(name = "pageNum") int page, @RequestParam(name = "pageSize") int size) {
        return productService.findAllProducts(page, size);
    }

    @GetMapping("/category/{url}")
    public Page<Product> getProductsByCategory(
            @PathVariable String url,
            @RequestParam(name = "pageNum") int page,
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "colors", required = false)List<String> colors,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") int minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "0") int maxPrice
    ) throws NotFoundException {

        return productService.getProductsByCategoryUrl(
                url, page, size, colors, maxPrice, minPrice);
    }


    //TODO: unused
    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam(name = "pageNum") int page,
            @RequestParam(name = "pageSize") int size,
            @RequestBody Map<String, String> searchMap
    ) {

        return productService.searchProducts(page, size, searchMap.get("searchRequest"));
    }

    @PostMapping
    public void addProduct(@RequestPart(name = "image") MultipartFile file,@Valid @RequestPart(name = "product") Product product) throws Exception {
        productService.addProduct(product, file);
    }

    @PutMapping
    public void updateProduct(@RequestPart(name = "image", required = false) MultipartFile file, @Valid @RequestPart(name = "product") Product product) throws Exception {
        productService.updateProduct(product, file);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) throws NotFoundException {
        productService.deleteProduct(id);
    }

    @GetMapping("/colors")
    public List<String> getAllColors() {
        return productService.getAllColors();
    }

    @PostMapping("/exist")
    public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", productService.isProductExist(payload.get("name"), payload.get("color")));
    }

}
