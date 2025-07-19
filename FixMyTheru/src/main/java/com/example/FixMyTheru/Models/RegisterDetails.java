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
public class RegisterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Lob
    @Column(nullable = true)
    private byte[] profileimage;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String address;


    @OneToMany(mappedBy = "registerDetails")
    @ToString.Exclude
    private List<Comments> comments;


    @OneToMany(mappedBy = "registerDetails")
    @ToString.Exclude
    private List<Issues>issues;

    @OneToMany(mappedBy = "maintainenceDetails")
    @ToString.Exclude
    private List<Issues> maintaience;

    @OneToOne(mappedBy = "maintaience")
    @ToString.Exclude
    private Workupdate maintainence;
}
