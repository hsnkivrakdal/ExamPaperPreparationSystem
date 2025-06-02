package com.example.eppms.repositories;

import com.example.eppms.models.Exampaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamPaperRepository extends JpaRepository<Exampaper, Integer> {
}
