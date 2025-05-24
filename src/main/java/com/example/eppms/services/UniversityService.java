package com.example.eppms.services;

import com.example.eppms.models.University;
import com.example.eppms.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

@Service
public class UniversityService extends BusinessServiceImplementation<University, Integer> {
    public UniversityService(UniversityRepository repository) {
        super(repository);
    }
}