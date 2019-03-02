package ua.shop.kdu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.shop.kdu.entities.Category;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.CategoryRepo;
import ua.shop.kdu.repositories.ProductRepo;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ProductImageService productImageService;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo, ProductImageService productImageService) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.productImageService = productImageService;
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
        product.setImageName(imageName);
        productRepo.save(product);
    }

    public Iterable<Product> getProductsByCategoryUrl(String categoryUrl) {
        Optional category = categoryRepo.findFirstByCategoryUrl(categoryUrl);
        if (category.isPresent()) {
            return productRepo.findByCategory((Category) category.get());
        }
        return null;
    }

    public Iterable<Product> findAllProducts() {
        return productRepo.findAll();
    }
}
