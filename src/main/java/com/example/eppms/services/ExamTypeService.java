package com.example.eppms.services;

import com.example.eppms.models.Examtype;
import com.example.eppms.repositories.ExamTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamTypeService extends BusinessServiceImplementation<Examtype, Integer> {
    public ExamTypeService(ExamTypeRepository repository) {
        super(repository);
    }
}
