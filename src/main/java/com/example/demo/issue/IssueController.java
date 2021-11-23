package com.example.demo.issue;


import com.example.demo.comment.Comment;
import com.example.demo.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class IssueController {

    private final IssueService issueService;
    private final CommentService commentService;

    @Autowired
    public IssueController(IssueService issueService, CommentService commentService) {
        this.issueService = issueService;
        this.commentService = commentService;
    }

    @GetMapping("/issues")
    public String findAll(Model model) {
        List<Issue> list = issueService.findAll();
        model.addAttribute("issues", list);
        return "issue_list";
    }

    @GetMapping("/create_issue")
    public String createIssueForm(Model model) {
        model.addAttribute("issue", new Issue());
        return "create_issue";
    }

    @PostMapping("/create_issue")
    public String createIssue(Issue issue) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(new Date());
        issue.setDate(date);

        issueService.save(issue);
        return "redirect:/issues";
    }

    @GetMapping("/issues/{issueId}")
    public String showIssue(@PathVariable("issueId") Long issueId, Model model)   {
        Issue issue = this.issueService.findById(issueId);
        List<Comment> comments = commentService.findCommentsByIssueId(issueId);
        model.addAttribute("issue", issue);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "issue_details";
    }

    @PostMapping("/issues/{issueId}")
    public String updateStatus(@PathVariable("issueId") Long id, @ModelAttribute Issue issue,
                               @ModelAttribute Comment comment) {
        Issue oldIssue = issueService.findById(id);
        oldIssue.setStatus(issue.getStatus());
        comment.setIssue(oldIssue);
        List<Comment> comments = oldIssue.getComments();
        comments.add(comment);
        issue.setComments(comments);
        issueService.save(oldIssue);
        return "redirect:/issues";
    }

}
