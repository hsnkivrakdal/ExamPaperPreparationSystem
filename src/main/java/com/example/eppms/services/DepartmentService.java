package com.example.eppms.services;

import com.example.eppms.models.Department;
import com.example.eppms.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BusinessServiceImplementation<Department, Integer> {
    public DepartmentService(DepartmentRepository repository) {
        super(repository);
    }
}