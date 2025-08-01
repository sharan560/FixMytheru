package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.IssuesDto;
import com.example.FixMyTheru.Models.Issues;
import com.example.FixMyTheru.Services.IssuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("Issue")
@CrossOrigin("*")
public class IssueController {

    @Autowired
    private IssuseService issuseService;


/// Add an issue in the table

        @PostMapping("/addIssue")
        public ResponseEntity<String> addIssue(
                @RequestPart("issue") Issues issues,
                @RequestPart("images") MultipartFile[] images
        ) {
            System.out.println("Issue JSON: " + issues);
            System.out.println("Date: " + issues.getIssueDate());
            System.out.println("Time: " + issues.getIssueTime());

            boolean success = issuseService.addissueWithImages(issues,images);
            return ResponseEntity.ok(success ? "Add issue successfully" : "Add issue failed");
        }

/// Get all Issues
///
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
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

    @GetMapping("/get/{id}")
    public IssuesDto getIssueById(@PathVariable int id){
        return issuseService.getByid(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getIssue/status")
    public List<IssuesDto> getIssueStatus(){
        return issuseService.getIssueByStatus("NOT_STARTED");
    }

    @GetMapping("/getIssue/employee/{id}")
    public List<IssuesDto> fetchIssuesByEmployeeid(@PathVariable int id){

        System.out.println(issuseService.getissuesBymaintanceid(id));
        return issuseService.getissuesBymaintanceid(id);
    }
/// assgin the maintence employee  to paticular issue
///
//    @PreAuthorize("permitAll()")
//    @PutMapping("/assign/{Issueid}/{maintanceid}")
//    public String assignIssue(@PathVariable int  Issueid,@PathVariable int   maintanceid){
//        System.out.println("hello");
//       if( issuseService.assignMaintanenceEmployee(Issueid,maintanceid)) return "Assign issue successfully";
//       return "Assign issue failed";
//
//    }

//@PutMapping("/assign/{userId}/{issueId}")
//@PreAuthorize("hasRole('ADMIN')")
//public ResponseEntity<String> assignIssue(@PathVariable int userId, @PathVariable int issueId) {
//    // your logic here
//    return ResponseEntity.ok("Assigned successfully");
//}

@PutMapping("/assign/{id1}/{id2}")
public ResponseEntity<String> assign(@PathVariable int id1, @PathVariable int id2) {
    issuseService.assignMaintanenceEmployee(id1,id2);
    return ResponseEntity.ok("Assigned successfully");
}




}
