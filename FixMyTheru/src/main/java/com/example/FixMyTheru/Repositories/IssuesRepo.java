package com.example.FixMyTheru.Repositories;

import com.example.FixMyTheru.Models.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepo extends JpaRepository<Issues, Integer> {




    List<Issues> findByRegisterDetails_Id(int id);

    List<Issues> findByMaintainenceDetails_Id(int maintainenceDetailsId);

    List<Issues> findByIssueStatus(String IssueStatus);


}
