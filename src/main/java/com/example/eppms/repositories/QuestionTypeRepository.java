package com.example.eppms.repositories;

import com.example.eppms.models.Questiontype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTypeRepository extends JpaRepository<Questiontype, Integer> {
}
