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
@Table(name = "examtypes")
public class Examtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamTypeId", nullable = false)
    private Integer id;

    @Column(name = "ExamTypeTitle", nullable = false, length = 75)
    private String examTypeTitle;

    @OneToMany(mappedBy = "examType")
    private Set<Coursesexam> coursesexams = new LinkedHashSet<>();

}