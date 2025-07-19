package com.example.FixMyTheru.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CommnetId;

    private String Commnentdescription;

    private Date Commentdate;

    private LocalDateTime Commenttime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private RegisterDetails registerDetails;

    @ManyToOne
    @JoinColumn(name="issue_id")
    @ToString.Exclude
    private Issues issues;




}
