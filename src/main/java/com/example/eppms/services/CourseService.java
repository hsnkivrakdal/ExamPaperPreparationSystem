package com.example.eppms.services;

import com.example.eppms.models.Cours;
import com.example.eppms.models.Coursesexam;
import com.example.eppms.models.Program;
import com.example.eppms.repositories.CourseRepository;
import com.example.eppms.repositories.CoursesExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService extends BusinessServiceImplementation<Cours, Integer> {
    public CourseService(CourseRepository repository) {
        super(repository);
    }

    @Autowired
    public ProgramService programService;

    public List<Program> getProgram() {
        return programService.getAll();
    }
}