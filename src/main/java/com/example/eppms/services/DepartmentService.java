package com.example.eppms.services;

import com.example.eppms.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(Integer id);
    void deleteDepartment(Integer id);
    Department updateDepartment(Integer id, Department updatedDepartment);
}
