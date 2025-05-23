package com.example.eppms.services.repositories;

import com.example.eppms.models.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Integer> {
    boolean existsByUniversityName(String universityName);
}
