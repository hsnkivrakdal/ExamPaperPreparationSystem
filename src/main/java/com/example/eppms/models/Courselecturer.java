package com.example.eppms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courselecturers")
public class Courselecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseLecturerId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LecturerId")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TermId")
    private Academicterm term;

}