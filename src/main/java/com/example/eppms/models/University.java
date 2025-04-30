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
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniversityId", nullable = false)
    private Integer id;

    @Column(name = "UniversityName", nullable = false, length = 50)
    private String universityName;

    @Column(name = "UniversityAddress", nullable = false, length = 256)
    private String universityAddress;

    @Column(name = "UniversityWebsite", nullable = false, length = 60)
    private String universityWebsite;

    @OneToMany(mappedBy = "university")
    private Set<Faculty> faculties = new LinkedHashSet<>();

}