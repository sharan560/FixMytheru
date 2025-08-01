package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.WorkupdateDto;
import com.example.FixMyTheru.Enum.IssueStatus;
import com.example.FixMyTheru.Models.Images;
import com.example.FixMyTheru.Models.Issues;
import com.example.FixMyTheru.Models.RegisterDetails;
import com.example.FixMyTheru.Models.Workupdate;
import com.example.FixMyTheru.Repositories.ImagesRepo;
import com.example.FixMyTheru.Repositories.IssuesRepo;
import com.example.FixMyTheru.Repositories.RegisterDetailsRepo;
import com.example.FixMyTheru.Repositories.WorkupdateRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkupdateServices {

    @Autowired
    private EmailService emailService;
    @Autowired
    private WorkupdateRepo workupdateRepo;

    @Autowired
    private IssuseService issuseService;

    @Autowired
    private ImagesRepo imagesRepo;
    @Autowired
    private RegisterDetailsRepo registerDetailsRepo;

    @Autowired
    private IssuesRepo  issuesRepo;

    public boolean UpdateOnWork(Workupdate workupdate, List<MultipartFile> image) throws IOException {

            List<RegisterDetails> reg1= registerDetailsRepo.findById(workupdate.getUserid()).stream().toList();
            workupdate.setMaintaience(reg1);
            Issues issue =issuesRepo.findById(workupdate.getIssueid()).orElseThrow();
            issue.setIssueStatus(IssueStatus.COMPLETED.toString());
            workupdate.setIssues(issue);
            emailService.sendEmail(issue);
            List<Images>images=new ArrayList<>();
            for (MultipartFile file : image) {

                Images img=new Images();
                img.setUpdates(workupdate);
                img.setIssues(workupdate.getIssues());
                img.setImage(file.getBytes());
                images.add(img);
            }
            workupdate.setImages(images);
            workupdateRepo.save(workupdate);
            imagesRepo.saveAll(images);
            issuesRepo.save(issue);
           return true;
        }

    public List<WorkupdateDto> getallWorkupdate() {
        List<Workupdate> workupdates = workupdateRepo.findAllByOrderByWorkDateDescWorkTimeDesc();

        return workupdates.stream()
                .map(workupdate -> new WorkupdateDto(
                        workupdate.getIssues().getIssueName(),
                        workupdate.getWorkDescription(),
                        workupdate.getWorkDate(),
                        workupdate.getImages(),
                        workupdate.getMaintaience().stream()
                                .map(RegisterDetails::getUsername)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<String> getimage(int id) {
        List<Images>img= imagesRepo.findImagesByUpdates_UpdateId( id);
        List<byte[]>images=new ArrayList<>();
        for(Images i:img){
            images.add(i.getImage());
        }
        List<String> base64Images = images.stream()
                .map(image -> Base64.getEncoder().encodeToString(image))
                .toList();
        return base64Images;
    }
}
