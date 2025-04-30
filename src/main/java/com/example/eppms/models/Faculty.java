package com.example.eppms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacultyId", nullable = false)
    private Integer id;

    @Column(name = "FacultyName", nullable = false, length = 50)
    private String facultyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UniversityId")
    private University university;

    @OneToMany(mappedBy = "faculty")
    private Set<Department> departments = new LinkedHashSet<>();

}