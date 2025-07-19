package com.example.FixMyTheru.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String commentDescription;

    private LocalDateTime commentDate;

    private LocalDateTime commentTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private RegisterDetails registerDetails;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    @ToString.Exclude
    private Issues issues;
}
