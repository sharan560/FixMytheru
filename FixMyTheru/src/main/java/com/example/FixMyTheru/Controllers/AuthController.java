package com.example.FixMyTheru.Controllers;


import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Services.AuthServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthServices authServices;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDetails userDetails) {

        if(authServices.registerUser(userDetails)) return  "Register Success";
        else return "Register Failed";

    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterDetails loginDetails) {
        if(authServices.login(loginDetails)) return "login Success";
        return "login Failed";
    }
}
