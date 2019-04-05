package ua.shop.kdu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.shop.kdu.entities.User;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findFirstByUsernameOrEmail(String username, String email);

}
