package com.example.FixMyTheru.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class LoginDto {
    private String username;
    private String token;
    private String Role;
    private int id;

}
