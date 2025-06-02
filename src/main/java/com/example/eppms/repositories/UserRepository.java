package com.example.eppms.repositories;

import com.example.eppms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String userEmail);
    
    @Query("SELECT u FROM User u JOIN FETCH u.userinroles ur JOIN FETCH ur.role WHERE u.userEmail = :email")
    Optional<User> findByUserEmailWithRoles(String email);
} 