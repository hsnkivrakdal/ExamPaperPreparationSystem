package com.example.eppms.services.repositories;

import com.example.eppms.models.Lecturertitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturertitleRepository extends JpaRepository <Lecturertitle,Integer> {
}
