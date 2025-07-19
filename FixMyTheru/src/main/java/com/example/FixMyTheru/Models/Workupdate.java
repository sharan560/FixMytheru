package com.example.FixMyTheru.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Workupdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int updateId;

    @OneToOne
    @JoinColumn(name="issue_id")
    private Issues issues;
    private String Workdescription;

    @OneToMany(mappedBy = "updates")
    @ToString.Exclude
    private List<Images> images;

    @OneToOne
    @JoinColumn(name="maintenece_id")
    @ToString.Exclude
    private RegisterDetails maintaience;



}
