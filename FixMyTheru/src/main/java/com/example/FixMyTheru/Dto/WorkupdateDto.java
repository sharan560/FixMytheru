package com.example.FixMyTheru.Dto;


import com.example.FixMyTheru.Models.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkupdateDto {

    private String issueName;
    private String workDescription;
    private LocalDate workDate;
//    private List<String> images ;
    private List<Images> images;
    private  String maintence;

}
