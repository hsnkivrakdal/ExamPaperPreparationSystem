package com.example.eppms.models;

import jakarta.persistence.*;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacultyId")
    private Integer id;

    @Column(name = "FacultyName", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UniversityId")
    private University university;

    @OneToMany(mappedBy = "faculty")
    private java.util.List<Department> departments;

    public Faculty(String name, University university) {
        this.name = name;
        this.university = university;
    }

    public Faculty() {}

    public String getName() {
        return name;
    }

    public University getUniversity() {
        return university;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getFacultyName() {
        return name;
    }

    public void setFacultyName(String name) {
        this.name = name;
    }

    public java.util.List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(java.util.List<Department> departments) {
        this.departments = departments;
    }
}
