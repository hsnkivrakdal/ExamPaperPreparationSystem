package com.example.eppms.repositories;

import com.example.eppms.models.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Cours, Integer> {
}
