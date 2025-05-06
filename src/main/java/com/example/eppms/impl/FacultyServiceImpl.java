package com.example.eppms.impl;

import com.example.eppms.models.Faculty;
import com.example.eppms.repositories.FacultyRepository;
import com.example.eppms.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Optional<Faculty> getFacultyById(Integer id) {
        return facultyRepository.findById(id);
    }

    @Override
    public void deleteFaculty(Integer id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty updateFaculty(Integer id, Faculty updatedFaculty) {
        Optional<Faculty> existing = facultyRepository.findById(id);
        if (existing.isPresent()) {
            Faculty faculty = existing.get();
            faculty.setFacultyName(updatedFaculty.getFacultyName());
            faculty.setUniversity(updatedFaculty.getUniversity());
            return facultyRepository.save(faculty);
        }
        return null;
    }
}
