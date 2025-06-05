package com.example.eppms.strategy;

import com.example.eppms.models.Lecturer;

import java.util.List;

public interface LecturerStrategy {
    List<Lecturer> filter(List<Lecturer> lecturers);
}
