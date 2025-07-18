package com.example.FixMyTheru.Repositories;


import com.example.FixMyTheru.Models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterDetailsRepo  extends JpaRepository<RegisterDetails, Integer> {

    RegisterDetails findByUsername(String username);

    List<RegisterDetails> findByRole(String role);
}
