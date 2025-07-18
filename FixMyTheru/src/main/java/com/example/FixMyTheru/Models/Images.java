package com.example.FixMyTheru.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="issue_id")
    private Issues issues;

}
