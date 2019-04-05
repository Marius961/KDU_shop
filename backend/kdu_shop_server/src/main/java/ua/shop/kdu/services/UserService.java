package ua.shop.kdu.services;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.shop.kdu.entities.Role;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.repositories.UserRepo;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User applicationUser = userRepo.findByUsername(s);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(s);
        }
        return applicationUser;
    }

    public boolean createUser(User user) {
        if (userRepo.findFirstByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
            user.setRoles(Collections.singleton(Role.USER));
            try {
                userRepo.save(user);
                return true;
            } catch (HibernateException e) {
                return false;
            }
        }
        return false;
    }

    public Page<User> getAllUsers(int page, int size) {
        return userRepo.findAll(PageRequest.of(page, size));
    }

    public void setAdminRole(Long userId) throws NotFoundException {
        Optional opUser = userRepo.findById(userId);
        if (opUser.isPresent()) {
            User user = (User) opUser.get();
            user.addRole(Role.ADMIN);
            userRepo.save(user);
        } else throw new NotFoundException("Cannot find user with id: " + userId);
    }

    public void removeAdminRole(Long userId) throws NotFoundException {
        Optional<User> opUser = userRepo.findById(userId);
        if (opUser.isPresent()) {
            User user = opUser.get();
            user.deleteRole(Role.ADMIN);
            userRepo.save(user);
        } else throw new NotFoundException("Cannot find user with id: " + userId);
    }

    public boolean isRegistered(String username) throws HibernateException {
        return userRepo.findByUsername(username) != null;
    }

    public boolean isEmailExist(String email) {
        return userRepo.findByEmail(email) != null;
    }
}
