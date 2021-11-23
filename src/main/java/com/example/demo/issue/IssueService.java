package com.example.demo.issue;

import com.example.demo.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    private final IssueRepository repository;

    @Autowired
    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }

    public List<Issue> findAll() {
        return repository.findAll();
    }

    public void save(Issue issue) {
        repository.save(issue);
    }

    public Issue findById( Long id) {
        return repository.findById(id).get();
    }

    public void updateIssue( Long id, Issue issue) {
        Issue issue1 = repository.findById(id).get();
        issue1.setStatus(issue.getStatus());
        repository.save(issue1);
    }






    public void addComment(Long id, Comment comment) {
        Issue issue = repository.findById(id).get();
      //  issue1.setStatus(issue.getStatus());
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        issue.setComments(comments);
        repository.save(issue);
    }

}
