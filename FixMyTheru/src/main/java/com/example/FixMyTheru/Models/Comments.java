package com.example.FixMyTheru.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegisterDetails registerDetails;

    @ManyToOne
    @JoinColumn(name="issue_id")
    private Issues issues;




}
