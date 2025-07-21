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
        return List.of(new SimpleGrantedAuthority(registerDetails.getRole()));
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
