package com.example.eppms.services;

import com.example.eppms.models.University;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    University saveUniversity(University university);
    List<University> getAllUniversities();
    Optional<University> getUniversityById(Integer id);
    void deleteUniversity(Integer id);
    University updateUniversity(Integer id, University updatedUniversity);
}
