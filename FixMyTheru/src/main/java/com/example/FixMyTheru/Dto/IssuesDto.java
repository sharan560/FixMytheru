package com.example.FixMyTheru.Dto;

import com.example.FixMyTheru.Models.Images;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class IssuesDto {

    private int id;
    private String issuename;
    private String issuedescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime issuedate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    private LocalDateTime issuetime;
    private String issueType;
    private String issuestatus;

}
