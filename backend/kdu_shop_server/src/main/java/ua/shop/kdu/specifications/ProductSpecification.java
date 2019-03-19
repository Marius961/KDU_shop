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
}
