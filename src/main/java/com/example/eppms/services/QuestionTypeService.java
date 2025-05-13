package com.example.eppms.services;

import com.example.eppms.models.Questiontype;
import com.example.eppms.repositories.QuestionTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeService extends BusinessServiceImplementation<Questiontype, Integer>{
    public QuestionTypeService(QuestionTypeRepository repository) {
        super(repository);
    }
}
