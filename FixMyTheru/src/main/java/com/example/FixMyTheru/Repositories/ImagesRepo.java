package com.example.FixMyTheru.Repositories;

import com.example.FixMyTheru.Models.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepo extends JpaRepository<Images,Integer> {
    List <Images>findImagesByUpdates_UpdateId(int updatesUpdateId);
}
