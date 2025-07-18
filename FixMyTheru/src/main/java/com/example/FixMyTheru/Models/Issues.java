package com.example.FixMyTheru.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Issueid;

    @Column(nullable = false)
    private String IssueName;

    @Column(nullable = false)
    private String IssueDescription;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date IssueDate;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING ,pattern = "HH:mm:ss")
    private Date IssueTime;

    @Column(nullable = false)
    private String IssueLocation;

    private String IssueStatus;

    @Column(nullable = false)
    private String IssueType;

    @OneToMany(mappedBy = "issues")
    private List<Images> IssueImages;

    @OneToMany(mappedBy = "issues")
    private List<Comments> IssueCommnets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private RegisterDetails registerDetails;

    @ManyToOne
    @JoinColumn(name="maintainence_id")
    private RegisterDetails maintainenceDetails;

}
