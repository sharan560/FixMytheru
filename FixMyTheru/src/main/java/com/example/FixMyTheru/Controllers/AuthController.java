package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.LoginDto;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDetails userDetails) {
        try {
            boolean isRegistered = authServices.registerUser(userDetails);

            if (isRegistered) {
                return ResponseEntity.ok("Register Success");
            } else {
                return ResponseEntity.badRequest().body("Register Failed");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterDetails loginDetails) {
        try {
            LoginDto ld = authServices.login(loginDetails);
            return ResponseEntity.ok(ld);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @GetMapping("/debug/roles")
    public ResponseEntity<?> debugRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth.getAuthorities().toString());
    }
}