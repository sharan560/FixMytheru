package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.CommentsDto;
import com.example.FixMyTheru.Models.Comments;
import com.example.FixMyTheru.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addcomment")
    public String addComment(@RequestBody Comments comment){
        System.out.println(comment);
        if(commentService.addComment(comment)) return "Added comment";
        else return "Failed to add comment";

    }

    @GetMapping("/get/{issueid}")
    public List<CommentsDto> getAllCommnets(@PathVariable int issueid)
    {
        return commentService.getAllcomments(issueid);
    }
}
