package com.example.FixMyTheru.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issueid;

    @Column(nullable = false)
    private String IssueName;

    @Column(nullable = false)
    private String IssueDescription;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime IssueDate;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING ,pattern = "HH:mm:ss")
    private LocalDateTime IssueTime;

    @Column(nullable = false)
    private String IssueLocation;

    @Column(name = "IssueStatus")
    private String issueStatus;


    @Column(nullable = false)
    private String IssueType;

    @OneToMany(mappedBy = "issues")
    @ToString.Exclude
    private List<Images> IssueImages;

    @OneToMany(mappedBy = "issues")
    @ToString.Exclude
    private List<Comments> IssueCommnets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private RegisterDetails registerDetails;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="maintainence_id")
    private RegisterDetails maintainenceDetails;

}
