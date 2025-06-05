package com.example.eppms.strategy;

import com.example.eppms.models.Lecturer;
import java.util.List;
import java.util.stream.Collectors;

public class WithPhoneLecturerStrategy implements LecturerStrategy {
    @Override
    public List<Lecturer> filter(List<Lecturer> lecturers) {
        return lecturers.stream()
                .filter(l -> l.getLecturerPhoneNumber() != null && !l.getLecturerPhoneNumber().isBlank())
                .collect(Collectors.toList());
    }
}
