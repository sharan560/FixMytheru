package com.example.FixMyTheru.Dto;

import com.example.FixMyTheru.Models.RegisterDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private RegisterDetails registerDetails;

    public UserPrincipal(RegisterDetails registerDetails) {
        this.registerDetails = registerDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String role = registerDetails.getRole();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        System.out.println("User role: " + role);
        System.out.println("GrantedAuthority: " + new SimpleGrantedAuthority(role));

        return List.of(new SimpleGrantedAuthority(role));
    }


    @Override
    public String getPassword() {
        return registerDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return registerDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
