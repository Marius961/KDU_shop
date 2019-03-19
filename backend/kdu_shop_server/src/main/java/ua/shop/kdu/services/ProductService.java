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
import ua.shop.kdu.repositories.CategoryRepo;
import ua.shop.kdu.repositories.ProductRepo;
import ua.shop.kdu.specifications.ProductSpecification;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.springframework.data.jpa.domain.Specification.where;
import static ua.shop.kdu.specifications.ProductSpecification.productByCategoryUrl;
import static ua.shop.kdu.specifications.ProductSpecification.productByColors;

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

    public Page<Product> getProductsByCategoryUrl(String categoryUrl, int page, int size, List<String> colors) throws NotFoundException {
        Optional category = categoryRepo.findFirstByCategoryUrl(categoryUrl);
        if (category.isPresent()) {

            Specification<Product> endSpecification = productByCategoryUrl(categoryUrl);

            if (colors != null) {
                endSpecification = endSpecification.and(productByColors(colors));
            }

            return productRepo.findAll(endSpecification, PageRequest.of(page, size));
        } else throw new NotFoundException("Cannot find category with URL: " + categoryUrl);
    }

    public Page<Product> findAllProducts(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }
}
