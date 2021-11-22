package com.example.demo.issue;

import com.example.demo.comment.Comment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String description;

    private Status status;

    private String date;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<Comment> comments;

}