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
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getAddress()
                ))
                .collect(Collectors.toList());

    }

    public UserDetailsDto getuserByid(int id) {

        RegisterDetails user= registerDetailsRepo.findById(id).orElseThrow();
        return new UserDetailsDto(user.getId(), user.getName(),user.getEmail(),user.getAddress());
    }

    public List<UserDetailsDto> getUserByRole(String role) {
            List<RegisterDetails> reg=registerDetailsRepo.findByRole(role);

            return reg.stream()
                    .map(user-> new UserDetailsDto(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getAddress()
                    )).collect(Collectors.toList());

    }
}
