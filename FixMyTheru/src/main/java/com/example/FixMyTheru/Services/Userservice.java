package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.UserDetailsDto;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Userservice {

    @Autowired
    private RegisterDetailsRepo registerDetailsRepo;



    public List<UserDetailsDto> getallusers() {

        List<RegisterDetails> users=registerDetailsRepo.findAll();
        return users.stream()
                .map(user -> new UserDetailsDto(
                        user.getName(),
                        user.getEmail(),
                        user.getProfileimage()
                ))
                .collect(Collectors.toList());

    }

    public UserDetailsDto getuserByid(int id) {

        RegisterDetails user= registerDetailsRepo.findById(id).get();
        return new UserDetailsDto(user.getName(),user.getEmail(),user.getProfileimage());
    }

}
