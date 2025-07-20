package com.example.FixMyTheru.Repositories;

import com.example.FixMyTheru.Models.Workupdate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkupdateRepo extends JpaRepository<Workupdate, Integer> {
    List<Workupdate> findAllByOrderByWorkDateDescWorkTimeDesc();


}
