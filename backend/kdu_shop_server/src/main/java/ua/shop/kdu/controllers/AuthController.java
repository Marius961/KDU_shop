package ua.shop.kdu.controllers;

import org.hibernate.result.Output;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.shop.kdu.entities.User;
import ua.shop.kdu.services.UserService;
import ua.shop.kdu.utils.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

import static ua.shop.kdu.filters.SecurityConstants.HEADER_STRING;
import static ua.shop.kdu.filters.SecurityConstants.TOKEN_PREFIX;

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
    public ResponseEntity<Void> signUp(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean isSuccess = userService.createUser(user);
        if (isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/is-registered")
    public Map<String, Boolean> checkUsername(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isRegistered", userService.isRegistered(payload.get("username")));
    }

    @PostMapping("/is-email-exist")
    public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", userService.isEmailExist(payload.get("email")));
    }
}
