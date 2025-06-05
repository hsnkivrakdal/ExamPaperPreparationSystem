package com.example.eppms.strategy;

import com.example.eppms.models.Lecturer;
import java.util.List;

public class LecturerFilterContext {
    private LecturerStrategy strategy;

    public void setStrategy(LecturerStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Lecturer> executeStrategy(List<Lecturer> lecturers) {
        return strategy.filter(lecturers);
    }
}
