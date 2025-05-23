package com.example.eppms.services;

import com.example.eppms.models.Cours;
import com.example.eppms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService extends BusinessServiceImplementation<Cours, Integer>{
    public CourseService(CourseRepository repository) {
        super(repository);
    }
}
