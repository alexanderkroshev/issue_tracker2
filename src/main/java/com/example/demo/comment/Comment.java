package com.example.demo.comment;


import com.example.demo.issue.Issue;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String text;

    @ManyToOne()
    @JoinColumn(name = "issue_id")
    private Issue issue;
}
