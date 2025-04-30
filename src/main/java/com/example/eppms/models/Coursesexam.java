package com.example.eppms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coursesexams")
public class Coursesexam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseExamId", nullable = false)
    private Integer id;

    @Column(name = "CourseExamTitle", nullable = false, length = 200)
    private String courseExamTitle;

    @Column(name = "CourseExamDate", nullable = false)
    private Instant courseExamDate;

    @Column(name = "CourseExamCapacity", nullable = false)
    private Short courseExamCapacity;

    @Column(name = "CourseExamSection", nullable = false)
    private Short courseExamSection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ExamTypeId")
    private Examtype examType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseId")
    private Cours course;

    @OneToMany(mappedBy = "courseExam")
    private Set<Exampaper> exampapers = new LinkedHashSet<>();

}