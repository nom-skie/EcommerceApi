package com.ws101.Alastoy_Espano.EcommerceApi.controller;

import com.ws101.Alastoy_Espano.EcommerceApi.dto.RegisterRequest;
import com.ws101.Alastoy_Espano.EcommerceApi.model.User;
import com.ws101.Alastoy_Espano.EcommerceApi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Handles authentication-related endpoints like registration.
 *
 * @author Alastoy, Españo
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user account.
     * The password is hashed before saving — never stored as plain text.
     *
     * @param request the registration data (username, password, role)
     * @return 201 Created with a success message, or 400 if username is taken
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        if (!request.getRole().equals("ROLE_USER") && !request.getRole().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role must be either ROLE_USER or ROLE_ADMIN");
        }


        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");

    }

}
