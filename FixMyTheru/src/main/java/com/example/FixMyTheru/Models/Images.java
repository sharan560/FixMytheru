package com.example.FixMyTheru.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageid;

    @Lob
    private byte[] image;


    @ManyToOne(optional = false)
    @JoinColumn(name="issue_id")
    @ToString.Exclude
    private Issues issues;

    @ManyToOne
    @JoinColumn(name = "update_id")
    @ToString.Exclude
    private Workupdate updates;

}
