package ua.shop.kdu.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public AuthController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
    }

    @PostMapping("/is-registered")
    public Map<String, Boolean> checkUsername(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isRegistered", userService.isRegistered(payload.get("username")));
    }

    @PostMapping("/is-email-exist")
    public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", userService.isEmailExist(payload.get("email")));
    }

    @PostMapping("/change-password")
    public void changePassword(
            @RequestBody Map<String, String> passwordData,
            Principal principal)
    {
        userService.changePassword(
                passwordData.get("oldPassword"),
                passwordEncoder.encode(passwordData.get("newPassword")),
                principal);
    }

    @PostMapping("/change-email")
    public void changeEmail(@RequestBody Map<String, String> payload, Principal principal) {
        userService.changeEmail(payload.get("email") ,principal);
    }
}
