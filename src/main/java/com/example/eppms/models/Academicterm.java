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
@Table(name = "academicterms")
public class Academicterm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TermId", nullable = false)
    private Integer id;

    @Column(name = "TermTitle", nullable = false, length = 50)
    private String termTitle;

    @OneToMany(mappedBy = "term")
    private Set<Courselecturer> courselecturers = new LinkedHashSet<>();

}