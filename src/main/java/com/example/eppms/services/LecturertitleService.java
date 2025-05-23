package com.example.eppms.services;

import com.example.eppms.models.Lecturertitle;
import com.example.eppms.repositories.LecturertitleRepository;
import org.springframework.stereotype.Service;

@Service
public class LecturertitleService extends BusinessServiceImplementation<Lecturertitle,Integer> {

    public LecturertitleService(LecturertitleRepository repository) {
        super(repository);
    }
}
