package com.eventbooking.tickets.controller;

import com.eventbooking.tickets.dto.LoginRequest;
import com.eventbooking.tickets.dto.RegisterRequest;
import com.eventbooking.tickets.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;//can be also (private AuthService authService)

    public AuthController(AuthService authService) {
        this.authService = authService;//constructor injection
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }
}