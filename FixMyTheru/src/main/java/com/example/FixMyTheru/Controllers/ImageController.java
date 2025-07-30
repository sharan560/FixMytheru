package com.example.FixMyTheru.Controllers;

import com.example.FixMyTheru.Dto.ImageDto;
import com.example.FixMyTheru.Services.ImageSerivces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageSerivces imageSerivces;

    @GetMapping("/getall")
    public List<ImageDto> getAllImages() {
        return imageSerivces.getAllImages();
    }
//    @GetMapping("/{id}")
//    public List<ImageDto> getImageById(@PathVariable String id) {
//        return imageSerivces.getImagebyid(id);
//    }
}
