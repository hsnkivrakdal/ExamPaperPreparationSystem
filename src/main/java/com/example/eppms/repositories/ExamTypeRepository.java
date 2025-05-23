package com.example.eppms.repositories;

import com.example.eppms.models.Examtype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTypeRepository extends JpaRepository<Examtype, Integer> {
}
