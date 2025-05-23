package com.example.eppms.services;

import com.example.eppms.models.Coursesexam;
import com.example.eppms.repositories.CoursesExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesExamService extends BusinessServiceImplementation<Coursesexam, Integer> {
    public CoursesExamService(CoursesExamRepository repository) {
        super(repository);
    }
}
