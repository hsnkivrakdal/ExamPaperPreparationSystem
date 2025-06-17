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
    private String universityName;

    @Column(name = "UniversityWebsite", length = 200)
    private String universityWebsite;

    @Column(name = "UniversityAddress", length = 500)
    private String universityAddress;

    public University() {}

    public University(String universityName) {
        this.universityName = universityName;
    }

    public University(String universityName, String universityWebsite, String universityAddress) {
        this.universityName = universityName;
        this.universityWebsite = universityWebsite;
        this.universityAddress = universityAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityWebsite() {
        return universityWebsite;
    }

    public void setUniversityWebsite(String universityWebsite) {
        this.universityWebsite = universityWebsite;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    // Legacy method for backward compatibility
    public String getName() {
        return universityName;
    }

    public void setName(String name) {
        this.universityName = name;
    }

    @Override
    public String toString() {
        return universityName;
    }
}
