package com.example.FixMyTheru.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class ImageDto {

    List<String>images;
    int issue_id;
}
