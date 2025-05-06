package com.example.eppms.services;

import com.example.eppms.models.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Faculty saveFaculty(Faculty faculty);
    List<Faculty> getAllFaculties();
    Optional<Faculty> getFacultyById(Integer id);
    void deleteFaculty(Integer id);
    Faculty updateFaculty(Integer id, Faculty updatedFaculty);
}
