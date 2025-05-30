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
@Table(name = "courses")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId", nullable = false)
    private Integer id;

    @Column(name = "CourseTitle", nullable = false, length = 50)
    private String courseTitle;

    @Column(name = "CourseCode", nullable = false, length = 50)
    private String courseCode;

    @Column(name = "CourseCredit", nullable = false)
    private Short courseCredit;

    @Column(name = "CourseQuota", nullable = false)
    private Short courseQuota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProgramId")
    private Program program;

    @OneToMany(mappedBy = "course")
    private Set<Coursesexam> coursesexams = new LinkedHashSet<>();

    @Override
    public String toString() {
        return courseTitle;
    }
}