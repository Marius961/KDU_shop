package ua.shop.kdu.services;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.shop.kdu.entities.ProductImage;
import ua.shop.kdu.repositories.ProductImageRepo;

import java.io.IOException;

@Service
public class ProductImageService {

    private ProductImageRepo productImageRepo;

    public ProductImageService(ProductImageRepo productImageRepo) {
        this.productImageRepo = productImageRepo;
    }


    public ProductImage saveImage(MultipartFile file) throws InvalidFileNameException, IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")) {
            throw new InvalidFileNameException("Invalid file name", fileName);
        }
        ProductImage image = new ProductImage(fileName, file.getContentType(), file.getBytes());

        productImageRepo.save(image);

        return image;
    }
}
