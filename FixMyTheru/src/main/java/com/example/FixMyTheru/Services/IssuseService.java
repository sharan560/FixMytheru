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
                        issue.getIssueStatus(),
                        issue.getIssueImages()
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
                        issues1.getIssueStatus(),
                        issues1.getIssueImages()
                )).collect(Collectors.toList());

    }

    public boolean assignMaintanenceEmployee(int issueid, int maintanceid) {
            Issues issues=issuesRepo.findById(issueid).orElseThrow();
            RegisterDetails maintanence=registerDetailsRepo.findById(issueid).orElseThrow();
            issues.setMaintainenceDetails(maintanence);
            issuesRepo.save(issues);
            return true;
    }
}
