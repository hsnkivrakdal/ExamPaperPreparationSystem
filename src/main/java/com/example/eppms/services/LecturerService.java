package com.example.eppms.services;

import com.example.eppms.models.Lecturer;
import com.example.eppms.repositories.LecturerRepository;
import org.springframework.stereotype.Service;

@Service
public class LecturerService extends BusinessServiceImplementation<Lecturer,Integer> {

    public LecturerService(LecturerRepository repository) {
        super(repository);
    }
}
