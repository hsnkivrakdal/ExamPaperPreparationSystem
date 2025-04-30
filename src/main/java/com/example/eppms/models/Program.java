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
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramId", nullable = false)
    private Integer id;

    @Column(name = "ProgramName", nullable = false, length = 50)
    private String programName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    private Department department;

    @OneToMany(mappedBy = "program")
    private Set<Cours> courses = new LinkedHashSet<>();

}