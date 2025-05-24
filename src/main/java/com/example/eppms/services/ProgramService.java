package com.example.eppms.services;

import com.example.eppms.models.Program;
import com.example.eppms.repositories.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService extends BusinessServiceImplementation<Program, Integer> {
    public ProgramService(ProgramRepository repository) {
        super(repository);
    }
}