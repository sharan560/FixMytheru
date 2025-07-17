package com.example.FixMyTheru.Controllers;


import com.example.FixMyTheru.Dto.UserDetailsDto;
import com.example.FixMyTheru.Services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {


    @Autowired
    private Userservice userservice;

    @GetMapping("")
    public List<UserDetailsDto> getallUsers() {
        return userservice.getallusers();
    }

    @GetMapping("/{id}")
    public UserDetailsDto getUser(@PathVariable int id) {
        return userservice.getuserByid(id);
    }
}
