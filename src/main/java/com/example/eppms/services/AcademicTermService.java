package com.example.eppms.services;

import com.example.eppms.models.Academicterm;
import com.example.eppms.repositories.AcademicTermRepository;
import org.springframework.stereotype.Service;


@Service
public class AcademicTermService extends BusinessServiceImplementation<Academicterm, Integer> {
    public AcademicTermService(AcademicTermRepository repository) {
        super(repository);
    }
}
