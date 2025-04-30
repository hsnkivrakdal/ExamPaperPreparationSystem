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
@Table(name = "lecturertitle")
public class Lecturertitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TitleId", nullable = false)
    private Integer id;

    @Column(name = "TitleDefinition", nullable = false, length = 50)
    private String titleDefinition;

    @OneToMany(mappedBy = "title")
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

}