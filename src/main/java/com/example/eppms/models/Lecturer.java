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
@Table(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LecturerId", nullable = false)
    private Integer id;

    @Column(name = "LecturerFirstName", nullable = false, length = 50)
    private String lecturerFirstName;

    @Column(name = "LecturerLastName", nullable = false, length = 50)
    private String lecturerLastName;

    @Column(name = "LecturerPhoneNumber", nullable = false, length = 15)
    private String lecturerPhoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TitleId")
    private Lecturertitle title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @OneToMany(mappedBy = "lecturer")
    private Set<Courselecturer> courselecturers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lecturer")
    private Set<Exampaper> exampapers = new LinkedHashSet<>();

}