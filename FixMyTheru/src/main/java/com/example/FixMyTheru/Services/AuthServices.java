package com.example.FixMyTheru.Services;


import com.example.FixMyTheru.Enum.Roles;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private RegisterDetailsRepo registerDetailsRepo;


    public boolean registerUser(RegisterDetails userDetails) {

        if(userDetails.getRole()==null)
        {
            userDetails.setRole(String.valueOf(Roles.ROLE_USER));
        }

        registerDetailsRepo.save(userDetails);
        return true;

    }

    public boolean login(RegisterDetails loginDetails) {

        RegisterDetails reg1=registerDetailsRepo.findByUsername(loginDetails.getUsername());
        if(reg1!=null){
            if(reg1.getUsername().equals(loginDetails.getUsername())&&
                    reg1.getPassword().equals(loginDetails.getPassword())){
                return true;
            }
        }
        return false;



    }
}
