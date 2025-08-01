package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.WorkupdateDto;
import com.example.FixMyTheru.Models.Workupdate;

import com.example.FixMyTheru.Services.WorkupdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ADMIN','MAINTANENCE')")
    @PostMapping("/add")
    public String UpdateOnWork( @RequestPart("workupdate") Workupdate workupdate,
                                @RequestPart("file") List< MultipartFile> image) throws IOException {

        System.out.println(workupdate);
        if(workupdateServices.UpdateOnWork(workupdate,image)) return "success";
        return "fail";

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getall")
    public List<WorkupdateDto>getallWorkupdate(){
        return workupdateServices.getallWorkupdate();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','MAINTANENCE')")
    @GetMapping("/image/{id}")
    public List<String> getImages(@PathVariable int id) {
       return workupdateServices.getimage(id);

    }

}
