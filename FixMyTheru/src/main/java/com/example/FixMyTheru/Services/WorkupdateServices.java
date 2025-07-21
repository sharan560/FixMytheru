package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.WorkupdateDto;
import com.example.FixMyTheru.Models.Images;
import com.example.FixMyTheru.Models.Workupdate;
import com.example.FixMyTheru.Repositories.ImagesRepo;
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
    private WorkupdateRepo workupdateRepo;

    @Autowired
    private ImagesRepo imagesRepo;

    public boolean UpdateOnWork(Workupdate workupdate, List<MultipartFile> image) throws IOException {
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
       return true;
    }

    public List<WorkupdateDto> getallWorkupdate() {
        List<Workupdate>workupdates=workupdateRepo.findAllByOrderByWorkDateDescWorkTimeDesc();
        return workupdates.stream()
                .map(workupdate -> new WorkupdateDto(
                        workupdate.getIssues().getIssueName(),
                        workupdate.getWorkDescription(),
                        workupdate.getWorkDate(),
                        workupdate.getImages(),
                        workupdate.getMaintaience().getUsername()
                        )).collect(Collectors.toList());
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
