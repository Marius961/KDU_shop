package ua.shop.kdu.specifications;

import org.springframework.data.jpa.domain.Specification;
import ua.shop.kdu.entities.Product;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> productByColors(List<String> colors) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!colors.isEmpty()) {
                for (String value : colors) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("color"), value)));
                }
            } else return null;

            return criteriaBuilder.or(predicates.toArray( new Predicate[0]));
        };
    }

    public static Specification<Product> productByCategoryUrl(String categoryUrl) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category").get("categoryUrl"), categoryUrl);
        };
    }

    public static Specification<Product> productByMinPrice(double price) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
        };
    }

    public static Specification<Product> productsByMaxPrice(double price) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
        };
    }

    public static Specification<Product> productBySearchRequest(String request) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            String text = request;
            if (!text.contains("%")) {
                text = "%" + text + "%";
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), text),
                    criteriaBuilder.like(root.get("color"), text),
                    criteriaBuilder.like(root.get("sizes"), text)
                    );
        };
    }

}
