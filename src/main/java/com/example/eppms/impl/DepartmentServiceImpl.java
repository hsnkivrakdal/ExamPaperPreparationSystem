package com.example.eppms.impl;

import com.example.eppms.models.Department;
import com.example.eppms.repositories.DepartmentRepository;
import com.example.eppms.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Integer id, Department updatedDepartment) {
        Optional<Department> existing = departmentRepository.findById(id);
        if (existing.isPresent()) {
            Department department = existing.get();
            department.setDepartmentName(updatedDepartment.getDepartmentName());
            department.setFaculty(updatedDepartment.getFaculty());
            return departmentRepository.save(department);
        }
        return null;
    }
}
