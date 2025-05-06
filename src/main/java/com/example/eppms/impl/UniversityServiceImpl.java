package com.example.eppms.impl;

import com.example.eppms.models.University;
import com.example.eppms.repositories.UniversityRepository;
import com.example.eppms.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public Optional<University> getUniversityById(Integer id) {
        return universityRepository.findById(id);
    }

    @Override
    public void deleteUniversity(Integer id) {
        universityRepository.deleteById(id);
    }

    @Override
    public University updateUniversity(Integer id, University updatedUniversity) {
        Optional<University> existing = universityRepository.findById(id);
        if (existing.isPresent()) {
            University university = existing.get();
            university.setUniversityName(updatedUniversity.getUniversityName());
            university.setUniversityAddress(updatedUniversity.getUniversityAddress());
            university.setUniversityWebsite(updatedUniversity.getUniversityWebsite());
            return universityRepository.save(university);
        }
        return null;
    }
}
