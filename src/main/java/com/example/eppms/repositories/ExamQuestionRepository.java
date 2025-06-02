package com.example.eppms.repositories;

import com.example.eppms.models.Exampaper;
import com.example.eppms.models.Examquestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionRepository extends JpaRepository<Examquestion,Integer> {
    List<Examquestion> findAllByIdIn(List<Integer> ids);
}
