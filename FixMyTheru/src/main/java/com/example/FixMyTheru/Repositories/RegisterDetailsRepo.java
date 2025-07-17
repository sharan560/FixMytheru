package com.example.FixMyTheru.Repositories;


import com.example.FixMyTheru.Models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDetailsRepo  extends JpaRepository<RegisterDetails, Integer> {

    RegisterDetails findByUsername(String username);
}
