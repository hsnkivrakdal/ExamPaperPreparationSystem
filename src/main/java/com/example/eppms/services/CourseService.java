package com.example.eppms.services;

import com.example.eppms.models.Coursesexam;
import com.example.eppms.repositories.CoursesExamRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends BusinessServiceImplementation<Coursesexam, Integer> {
    public CourseService(CoursesExamRepository repository) {
        super(repository);
    }
}