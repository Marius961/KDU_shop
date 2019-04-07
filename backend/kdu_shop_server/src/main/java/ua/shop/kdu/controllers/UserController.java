package ua.shop.kdu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.exceptions.NotFoundException;
import ua.shop.kdu.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(name = "pageNum") int page,
            @RequestParam(name = "pageSize") int size
    ) {
        return userService.getAllUsers(page, size);
    }

    @PostMapping("/set-admin/{id}")
    public void addAdminRole(@PathVariable Long id) throws NotFoundException {
        userService.setAdminRole(id);
    }

    @PostMapping("/remove-admin/{id}")
    public void removeAdminRole(@PathVariable Long id) throws NotFoundException {
        userService.removeAdminRole(id);
    }

    @GetMapping("/account-info")
    public User getAccountInfo(Principal principal) {
        return (User) userService.loadUserByUsername(principal.getName());
    }
}
