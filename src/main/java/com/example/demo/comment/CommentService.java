package com.example.demo.comment;

import com.example.demo.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findCommentsByIssueId(Long id){
       return commentRepository.findCommentsByIssueId(id);
    }

}
