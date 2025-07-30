package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.LoginDto;
import com.example.FixMyTheru.Enum.Roles;
import com.example.FixMyTheru.Jwt.JwtTokenProvider;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private RegisterDetailsRepo registerDetailsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public boolean registerUser(RegisterDetails userDetails) {
        if (userDetails.getRole() == null) {
            userDetails.setRole(String.valueOf(Roles.USER));
        }
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        registerDetailsRepo.save(userDetails);
        return true;
    }

    public LoginDto login(RegisterDetails loginDetails) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDetails.getUsername(),
                        loginDetails.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            RegisterDetails user = registerDetailsRepo.findByUsername(loginDetails.getUsername());
            String token = jwtTokenProvider.generateToken(authentication);

            LoginDto loginDto = new LoginDto();
            loginDto.setUsername(user.getUsername());
            loginDto.setToken(token);
            loginDto.setRole(user.getRole());
            loginDto.setId(user.getId());

            return loginDto;
        } else {
            throw new RuntimeException("Invalid Access");
        }
    }
}
