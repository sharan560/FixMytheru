package com.example.FixMyTheru.Controllers;


import com.example.FixMyTheru.Dto.LoginDto;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Services.AuthServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String registerUser(@RequestBody RegisterDetails userDetails) {

        if(authServices.registerUser(userDetails)) return  "Register Success";
        else return "Register Failed";

    }

    @PostMapping("/login")
    public LoginDto login(@RequestBody RegisterDetails loginDetails) {
        LoginDto ld= authServices.login(loginDetails) ;
        System.out.println(ld);
        return ld;
    }
    @GetMapping("/debug/roles")
    public String debugRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        return  auth.getAuthorities().toString();
    }

}
