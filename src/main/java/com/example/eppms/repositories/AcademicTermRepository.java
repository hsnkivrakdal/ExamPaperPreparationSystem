package com.example.eppms.repositories;

import com.example.eppms.models.Academicterm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTermRepository extends JpaRepository<Academicterm, Integer> {
}
