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
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentId", nullable = false)
    private Integer id;

    @Column(name = "DepartmentName", nullable = false, length = 50)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FacultyId")
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Program> programs = new LinkedHashSet<>();

}