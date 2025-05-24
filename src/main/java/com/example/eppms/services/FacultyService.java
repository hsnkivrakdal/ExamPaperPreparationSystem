package com.example.eppms.services;

import com.example.eppms.models.Faculty;
import com.example.eppms.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService extends BusinessServiceImplementation<Faculty, Integer> {
    public FacultyService(FacultyRepository repository) {
        super(repository);
    }
}