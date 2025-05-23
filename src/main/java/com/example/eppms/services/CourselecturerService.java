package com.example.eppms.services;

import com.example.eppms.models.Courselecturer;
import com.example.eppms.repositories.CourselecturerRepository;
import org.springframework.stereotype.Service;

@Service
public class CourselecturerService extends BusinessServiceImplementation<Courselecturer,Integer> {

    public CourselecturerService (CourselecturerRepository courselecturerRepository) {
        super(courselecturerRepository);
    }
}
