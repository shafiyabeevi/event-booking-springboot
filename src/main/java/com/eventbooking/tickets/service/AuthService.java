package com.eventbooking.tickets.service;

import com.eventbooking.tickets.dto.LoginRequest;
import com.eventbooking.tickets.dto.RegisterRequest;
import com.eventbooking.tickets.entity.User;
import com.eventbooking.tickets.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Registration
    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(encodedPassword);
        user.setRole(request.getRole());

        userRepository.save(user);


        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        System.out.println("Email received: " + request.getEmail());

        Optional<User> result =
                userRepository.findByEmail(request.getEmail());

        if (result.isEmpty()) {
            return "USER NOT FOUND: " + request.getEmail();
        }

        User user = result.get();

        System.out.println("User found: " + user.getEmail());
        System.out.println("Password from database: " + user.getPassword());

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            return "PASSWORD DOES NOT MATCH";
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return token;
    }
}