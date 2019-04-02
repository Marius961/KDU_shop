package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.shop.kdu.entities.CartItem;
import ua.shop.kdu.entities.Product;
import ua.shop.kdu.entities.User;

import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem, Long> {

    boolean existsByIdAndUser(Long id, User user);

    Optional<CartItem> findByIdAndUser(Long id, User user);

    Iterable<CartItem> findAllByUser(User user);

    Optional<CartItem> findByProductAndSizeAndUser(Product product, String size, User user);

    void deleteAllByProduct(Product product);
}
