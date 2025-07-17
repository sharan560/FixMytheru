package com.example.FixMyTheru.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDetailsDto {

    private String name;
    private String email;
    private byte[] image;


}
