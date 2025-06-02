package com.example.eppms.repositories;

import com.example.eppms.models.Questionoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuetionOptionsRepository extends JpaRepository<Questionoption, Integer> {
}
