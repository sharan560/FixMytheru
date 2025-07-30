package com.example.FixMyTheru.Controllers;


import com.example.FixMyTheru.Dto.UserDetailsDto;
import com.example.FixMyTheru.Services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UserController {


    @Autowired
    private Userservice userservice;


    @GetMapping("/getall")
    public List<UserDetailsDto> getallUsers() {
        return userservice.getallusers();
    }


    @GetMapping("/{id}")
    public UserDetailsDto getUser(@PathVariable int id) {
        return userservice.getuserByid(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{role}")
    public List<UserDetailsDto> getuserByrole(@PathVariable String role) {
        return userservice.getUserByRole(role);
    }

}
