package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.CommentsDto;
import com.example.FixMyTheru.Models.Comments;
import com.example.FixMyTheru.Repositories.CommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentsRepo commentsRepo;

    public boolean addComment(Comments comment) {
        commentsRepo.save(comment);
        return true;
    }
    public List<CommentsDto> getAllcomments(int issueid) {
        List<Comments> comments = commentsRepo.findAllByIssues_IssueidOrderByCommentDateDescCommentTimeDesc(issueid);

        return comments.stream()
                .map(comment -> new CommentsDto(
                        comment.getCommentDescription(),
                        comment.getRegisterDetails().getName(),
                        comment.getCommentDate(),
                        comment.getCommentTime())
                )
                .collect(Collectors.toList());
    }

}
