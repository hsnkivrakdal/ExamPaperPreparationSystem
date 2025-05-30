package com.example.eppms.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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
    @Column(name = "CourseExamId")
    private Integer id;

    @Column(name = "CourseExamTitle", nullable = false, length = 200)
    private String courseExamTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "CourseExamDate", nullable = false)
    private Date courseExamDate;

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
