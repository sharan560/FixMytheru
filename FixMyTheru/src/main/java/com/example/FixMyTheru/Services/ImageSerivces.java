package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Dto.ImageDto;
import com.example.FixMyTheru.Models.Images;
import com.example.FixMyTheru.Repositories.ImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Base64;

@Service
public class ImageSerivces {

    @Autowired
    private ImagesRepo imagesRepo;

    public List<ImageDto> getAllImages() {
        List<Images> allImages = imagesRepo.findAll();

        Map<Integer, List<String>> grouped = new HashMap<>();

        for (Images img : allImages) {
            if (img.getImage() != null && img.getIssues() != null) {
                String base64Image = Base64.getEncoder().encodeToString(img.getImage());
                int issueId = img.getIssues().getIssueid();
                grouped.computeIfAbsent(issueId, k -> new ArrayList<>()).add(base64Image);
            }
        }

        return grouped.entrySet()
                .stream()
                .map(entry -> new ImageDto(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }


//    public List<ImageDto> getImagebyid(String id) {
//
//        List<Images> img = imagesRepo.findImagesByReg
//
//    }
}
