package ua.shop.kdu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.shop.kdu.entities.Role;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.repositories.UserRepo;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class UserCreator {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCreator(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        addAdmin();


    }


    private void addAdmin() {
        User admin = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin"));
        admin.setRoles(new HashSet<>(Arrays.asList(Role.USER, Role.ADMIN)));
        userRepo.save(admin);
    }
}
