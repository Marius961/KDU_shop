package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.shop.kdu.entities.User;

public interface UserRepo extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findFirstByUsernameOrEmail(String username, String email);

}
