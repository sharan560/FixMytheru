package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.UserPrincipal;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    RegisterDetailsRepo registerDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        RegisterDetails user = registerDetailsRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
