package ua.shop.kdu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.CartItemRepo;
import ua.shop.kdu.repositories.CategoryRepo;
import ua.shop.kdu.repositories.OrderedItemRepo;
import ua.shop.kdu.repositories.ProductRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static ua.shop.kdu.specifications.ProductSpecification.*;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private OrderedItemRepo orderedItemRepo;
    private ProductImageService productImageService;
    private CartItemRepo cartItemRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo, OrderedItemRepo orderedItemRepo, ProductImageService productImageService, CartItemRepo cartItemRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.orderedItemRepo = orderedItemRepo;
        this.productImageService = productImageService;
        this.cartItemRepo = cartItemRepo;
    }

    public Product getProduct(long id) throws NotFoundException {
        Optional product =  productRepo.findById(id);
        if (product.isPresent()) {
            return (Product) product.get();
        }
        throw new NotFoundException("Can not find product with this id");
    }

    public void addProduct(Product product, MultipartFile file) throws Exception {
        String imageName = productImageService.saveImage(file);
        if (!isProductExist(product.getName(), product.getColor())) {
            product.setId(null);
            product.setImageName(imageName);
            if (product.getColor().trim().equals("")) {
                product.setColor(null);
            }
            productRepo.save(product);
        } else throw new IllegalArgumentException("Cannot add product. Product with this color and name already exist");
    }

    public void updateProduct(Product product, MultipartFile file) throws NotFoundException, IOException {
        Optional<Product> opProduct = productRepo.findById(product.getId());
        if (opProduct.isPresent()) {
            if (categoryRepo.existsById(product.getCategory().getId())) {
                if (file != null) {
                    product.setImageName(productImageService.saveImage(file));
                } else if (product.getImageName().equals("") || product.getImageName() == null) {
                    product.setImageName(opProduct.get().getImageName());
                }
                productRepo.save(product);
            } else throw new NotFoundException("Cannot find category fo updated product.");
        } else throw new NotFoundException("Cannot update product. Product with id: " + product.getId() + " is not exist");
    }

    public void deleteProduct(Long productId) throws NotFoundException {
        Optional<Product> opProduct = productRepo.findById(productId);
        if (opProduct.isPresent()) {
            if (!orderedItemRepo.existsByProduct(opProduct.get())) {
                cartItemRepo.deleteAllByProduct(opProduct.get());
                productRepo.deleteById(productId);
            } else throw new NotFoundException("Cannot delete product, because it is already ordered.");
        } else throw new NotFoundException("Cannot delete product. Product with id: "+ productId + " is not exist");
    }

    public Page<Product> searchProducts(int page, int size, String request) {
        Specification<Product> productsBySearchRequest = productBySearchRequest(request);
        return productRepo.findAll(productsBySearchRequest, PageRequest.of(page, size));
    }

    public Page<Product> getProductsByCategoryUrl(
            String categoryUrl,
             int page,
             int size,
             List<String> colors,
             int maxPrice,
             int minPrice
        ) throws NotFoundException {

        Optional category = categoryRepo.findFirstByCategoryUrl(categoryUrl);
        if (category.isPresent()) {

            Specification<Product> endSpecification = productByCategoryUrl(categoryUrl);

            if (colors != null) {
                endSpecification = endSpecification.and(productByColors(colors));
            }
            if (minPrice != 0) {
                endSpecification = endSpecification.and(productByMinPrice(minPrice));
            }
            if (maxPrice != 0) {
                endSpecification = endSpecification.and(productsByMaxPrice(maxPrice));
            }


            return productRepo.findAll(endSpecification, PageRequest.of(page, size));
        } else throw new NotFoundException("Cannot find category with URL: " + categoryUrl);
    }

    public List<Product> getMoreOrderedProducts() {
        return orderedItemRepo.getPopularProducts(PageRequest.of(0, 20));
    }

    public Page<Product> findAllProducts(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }

    public List<String> getAllColors() {
        return productRepo.findAllColors();
    }

    public boolean isProductExist(String name, String color) {
        return productRepo.existsByNameAndColor(name, color);
    }

}
