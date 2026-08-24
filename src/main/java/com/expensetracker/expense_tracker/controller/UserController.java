package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.model.User;
import com.expensetracker.expense_tracker.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return "Registration successful";
    }


    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public String login(
            @RequestBody User loginUser,
            HttpSession session) {

        return userRepository
                .findByUsername(loginUser.getUsername())
                .map(user -> {

                    if (passwordEncoder.matches(
                            loginUser.getPassword(),
                            user.getPassword())) {

                        session.setAttribute(
                                "loggedInUser",
                                user.getUsername()
                        );

                        return "Login successful";
                    }

                    return "Invalid password";
                })
                .orElse("User not found");
    }


    // =========================
    // CURRENT USER
    // =========================

    @GetMapping("/current-user")
    public String currentUser(HttpSession session) {

        String username =
                (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return "Not logged in";
        }

        return username;
    }


    // =========================
    // LOGOUT
    // =========================

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "Logout successful";
    }
}