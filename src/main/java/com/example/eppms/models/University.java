package com.example.eppms.models;

import jakarta.persistence.*;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniversityId")
    private Integer id;

    @Column(name = "UniversityName", nullable = false, length = 100)
    private String name;

    public University() {}

    public University(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
}
