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
@Table(name = "exampapers")
public class Exampaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamPaperId", nullable = false)
    private Integer id;

    @Column(name = "ExamPaperTime")
    private Short examPaperTime;

    @Column(name = "ExamVersion", length = 60)
    private String examVersion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseExamId")
    private Coursesexam courseExam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LecturerId")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "examPaper")
    private Set<Exampaperquestion> exampaperquestions = new LinkedHashSet<>();

}