package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.WorkupdateDto;
import com.example.FixMyTheru.Models.Workupdate;
import com.example.FixMyTheru.Repositories.WorkupdateRepo;
import com.example.FixMyTheru.Services.WorkupdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/update")
@CrossOrigin(origins = "*")
public class WorkupadateController {

    @Autowired
    private WorkupdateServices workupdateServices;

    @PostMapping("/add")
    public String UpdateOnWork( @RequestPart("workupdate") Workupdate workupdate,
                                @RequestPart("file") List< MultipartFile> image) throws IOException {


        if(workupdateServices.UpdateOnWork(workupdate,image)) return "success";
        return "fail";

    }

    @GetMapping("/getall")
    public List<WorkupdateDto>getallWorkupdate(){
        return workupdateServices.getallWorkupdate();
    }
    @GetMapping("/image/{id}")
    public List<String> getImages(@PathVariable int id) {
        List<byte[]> images = workupdateServices.getimage(id);


        List<String> base64Images = images.stream()
                .map(image -> Base64.getEncoder().encodeToString(image))
                .toList();

        return base64Images;
    }

}
