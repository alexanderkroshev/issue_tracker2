package com.example.demo.issue;


import com.example.demo.comment.Comment;
import com.example.demo.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String showIssue(@PathVariable("issueId") Long issueId, Model model) {
        Issue issue = this.issueService.findById(issueId);
        List<Comment> comments = commentService.findCommentsByIssueId(issueId);
        model.addAttribute("issue", issue);
        model.addAttribute("comments", comments);
        model.addAttribute("statuses", Status.values());
        return "issue_details";
    }

    @PatchMapping("/issues/{issueId}")
    public String updateStatus(@PathVariable("id") Long id, Issue issue) {
        issueService.updateIssue(id, issue);
        return "redirect:/issues";
    }


}
