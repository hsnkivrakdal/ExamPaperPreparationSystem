package com.example.eppms.services.repositories;

import com.example.eppms.models.Courselecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourselecturerRepository extends JpaRepository<Courselecturer, Integer> {

}

