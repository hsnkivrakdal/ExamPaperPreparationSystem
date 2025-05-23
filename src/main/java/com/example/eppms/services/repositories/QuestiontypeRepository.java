package com.example.eppms.services.repositories;

import com.example.eppms.models.Questiontype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestiontypeRepository extends JpaRepository <Questiontype,Integer> {
}
