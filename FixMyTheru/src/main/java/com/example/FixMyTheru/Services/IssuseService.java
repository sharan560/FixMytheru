package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.IssuesDto;
import com.example.FixMyTheru.Enum.IssueStatus;
import com.example.FixMyTheru.Models.Issues;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Repositories.IssuesRepo;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuseService {

    @Autowired
    private IssuesRepo issuesRepo;

    @Autowired
    private RegisterDetailsRepo registerDetailsRepo;

    public boolean addissue(Issues issues) {
        if(issues.getIssueStatus()==null){
            issues.setIssueStatus(String.valueOf(IssueStatus.NOT_STARTED));
        }
        issuesRepo.save(issues);
        return true;
    }

    public List<IssuesDto> fetchIssuesByUserid(int id) {
        List<Issues> issues = issuesRepo.findByRegisterDetails_Id(id);
        System.out.println(issues);
        return issues.stream()
                .map(issue -> new IssuesDto(
                        issue.getIssueid(),
                        issue.getIssueName(),
                        issue.getIssueDescription(),
                        issue.getIssueDate(),
                        issue.getIssueTime(),
                        issue.getIssueType(),
                        issue.getIssueStatus()
                ))
                .collect(Collectors.toList());
    }




    public List<IssuesDto> getAllIssuses() {
        List<Issues>issues= issuesRepo.findAll();

        return issues.stream()
                .map(issues1 ->  new IssuesDto(
                        issues1.getIssueid(),
                        issues1.getIssueName(),
                        issues1.getIssueDescription(),
                        issues1.getIssueTime(),
                        issues1.getIssueDate(),
                        issues1.getIssueType(),
                        issues1.getIssueStatus()
                )).collect(Collectors.toList());

    }

    public boolean assignMaintanenceEmployee(int issueid, int maintanceid) {
        // Find the issue by ID
        System.out.println(issueid);
        System.out.println(maintanceid);
        Issues issue = issuesRepo.findById(issueid).orElse(null);
        if (issue == null) {
            return false; // issue not found
        }

        RegisterDetails employee = registerDetailsRepo.findById(maintanceid).orElse(null);
        if (employee == null || !"MAINTANENCE".equalsIgnoreCase(employee.getRole())) {
            System.out.println("not");
            return false; // employee not found or not a maintenance staff
        }
        issue.setIssueStatus(IssueStatus.IN_PROGRESS.toString());
        System.out.println("yes");
        issue.setMaintainenceDetails(employee);
        issuesRepo.save(issue);
        return true;
    }




    public List<IssuesDto> getissuesBymaintanceid(int id) {
        List<Issues>issues= issuesRepo.findByMaintainenceDetails_Id(id);


        return issues.stream()
                .map(issues1 ->  new IssuesDto(
                        issues1.getIssueid(),
                        issues1.getIssueName(),
                        issues1.getIssueDescription(),
                        issues1.getIssueTime(),
                        issues1.getIssueDate(),
                        issues1.getIssueType(),
                        issues1.getIssueStatus()
                )).collect(Collectors.toList());

    }

    public List<IssuesDto> getIssueByStatus(String status) {
        List<Issues> issues = issuesRepo.findByIssueStatus(status);

        return issues.stream()
                .map(issue -> new IssuesDto(
                        issue.getIssueid(),
                        issue.getIssueName(),
                        issue.getIssueDescription(),
                        issue.getIssueTime(),
                        issue.getIssueDate(),
                        issue.getIssueType(),
                        issue.getIssueStatus()
                ))
                .collect(Collectors.toList());
    }

}
