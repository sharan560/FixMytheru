package com.example.FixMyTheru.Controllers;


import com.example.FixMyTheru.Dto.UserDetailsDto;
import com.example.FixMyTheru.Services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getall")
    public List<UserDetailsDto> getallUsers() {
        return userservice.getallusers();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','MAINTANENCE')")
    @GetMapping("/{id}")
    public UserDetailsDto getUser(@PathVariable int id) {
        return userservice.getuserByid(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/get/{role}")
    public List<UserDetailsDto> getuserByrole(@PathVariable String role) {
        return userservice.getUserByRole(role);
    }

}
