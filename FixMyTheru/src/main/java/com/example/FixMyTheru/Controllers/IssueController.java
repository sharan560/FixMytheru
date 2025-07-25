package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.IssuesDto;
import com.example.FixMyTheru.Models.Issues;
import com.example.FixMyTheru.Services.IssuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Issue")
public class IssueController {

    @Autowired
    private IssuseService issuseService;


/// Add an issue in the table

@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/addIssue")
    public String addIssuse(@RequestBody Issues issues){
        if(issuseService.addissue(issues)) return "Add issue successfully";
        return "Add issue failed";

    }

/// Get all Issues
///
@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getall")
    public List<IssuesDto> getAllIssues(){
        return issuseService.getAllIssuses();
    }

/// Get all the issues that are raised by an user
///
@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getIssue/{id}")
    public List<IssuesDto> fetchIssuesByUserid(@PathVariable int id){
        return issuseService.fetchIssuesByUserid(id);

    }

/// assgin the maintence employee  to paticular issue
///
@PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/assign/{Issueid}/{maintanceid}")
    public String assignIssue(@PathVariable int  Issueid,@PathVariable int   maintanceid){
       if( issuseService.assignMaintanenceEmployee(Issueid,maintanceid)) return "Assign issue successfully";
       return "Assign issue failed";

    }


}
