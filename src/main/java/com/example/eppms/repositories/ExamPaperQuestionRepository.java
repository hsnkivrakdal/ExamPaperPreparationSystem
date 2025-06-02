package com.example.eppms.repositories;

import com.example.eppms.models.Exampaperquestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamPaperQuestionRepository extends JpaRepository<Exampaperquestion, Integer> {
}
