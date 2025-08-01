package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.IssuesDto;
import com.example.FixMyTheru.Enum.IssueStatus;
import com.example.FixMyTheru.Models.Images;
import com.example.FixMyTheru.Models.Issues;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Repositories.ImagesRepo;
import com.example.FixMyTheru.Repositories.IssuesRepo;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuseService {

    @Autowired
    private IssuesRepo issuesRepo;

    @Autowired
    private RegisterDetailsRepo registerDetailsRepo;

    @Autowired
    private ImagesRepo imagesRepo;

    public boolean addissueWithImages(Issues issues, MultipartFile[] images) {
        try {

            if (issues.getIssueStatus() == null) {
                issues.setIssueStatus(String.valueOf(IssueStatus.NOT_STARTED));
            }
            System.out.println(issues.getUserId());

            RegisterDetails reg1= registerDetailsRepo.findById(issues.getUserId()).get();
            issues.setRegisterDetails(reg1);

            Issues savedIssue = issuesRepo.save(issues);

            List<Images> imageEntities = new ArrayList<>();

            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    Images img = new Images();
                    img.setImage(file.getBytes());
                    img.setIssues(savedIssue);
                    img.setUpdates(null);
                    imageEntities.add(img);
                }
            }

            if (!imageEntities.isEmpty()) {
                imagesRepo.saveAll(imageEntities);
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<IssuesDto> fetchIssuesByUserid(int id) {
        List<Issues> issues = issuesRepo.findByRegisterDetails_Id(id);
        return issues.stream()
                .map(issue -> new IssuesDto(
                        issue.getIssueid(),
                        issue.getIssueName(),
                        issue.getIssueDescription(),
                        issue.getIssueDate(),          // Correct: LocalDate
                        issue.getIssueTime(),          // Correct: LocalTime
                        issue.getIssueType(),
                        issue.getIssueStatus()
                ))
                .collect(Collectors.toList());
    }

    public List<IssuesDto> getAllIssuses() {
        List<Issues> issues = issuesRepo.findAll();
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

    public boolean assignMaintanenceEmployee(int issueid, int maintanceid) {
        Issues issue = issuesRepo.findById(issueid).orElse(null);
        if (issue == null) {
            return false;
        }

        RegisterDetails employee = registerDetailsRepo.findById(maintanceid).orElse(null);
        if (employee == null || !"MAINTANENCE".equalsIgnoreCase(employee.getRole())) {
            return false;
        }

        issue.setIssueStatus(IssueStatus.IN_PROGRESS.toString());
        issue.setMaintainenceDetails(employee);
        issuesRepo.save(issue);
        return true;
    }

    public List<IssuesDto> getissuesBymaintanceid(int id) {
        List<Issues> issues = issuesRepo.findByMaintainenceDetails_Id(id);
        return issues.stream()
                .map(issue -> new IssuesDto(
                        issue.getIssueid(),
                        issue.getIssueName(),
                        issue.getIssueDescription(),
                        issue.getIssueDate(),          // Correct: LocalDate
                        issue.getIssueTime(),          // Correct: LocalTime
                        issue.getIssueType(),
                        issue.getIssueStatus()
                ))
                .collect(Collectors.toList());
    }

    public List<IssuesDto> getIssueByStatus(String status) {
        List<Issues> issues = issuesRepo.findByIssueStatus(status);
        return issues.stream()
                .map(issue -> new IssuesDto(
                        issue.getIssueid(),
                        issue.getIssueName(),
                        issue.getIssueDescription(),
                        issue.getIssueDate(),          // Correct: LocalDate
                        issue.getIssueTime(),         // Correct: LocalTime
                        issue.getIssueType(),
                        issue.getIssueStatus()
                ))
                .collect(Collectors.toList());
    }

    public IssuesDto getByid(int id) {
        Issues issue= issuesRepo.findById(id).orElseThrow();
        return new IssuesDto(issue.getIssueid(),
                issue.getIssueName(),
                issue.getIssueDescription(),
                issue.getIssueDate(),
                issue.getIssueTime(),
                issue.getIssueType(),
                issue.getIssueStatus());
    }

    public void deleteissue(int issueid) {

        issuesRepo.deleteById(issueid);
    }
}
