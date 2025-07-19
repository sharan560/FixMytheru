package com.example.FixMyTheru.Repositories;

import com.example.FixMyTheru.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Long> {
    List<Comments> findAllByIssues_Issueid(int issueid);
    List<Comments> findAllByIssues_IssueidOrderByCommentDateDescCommentTimeDesc(int issueid);


}
