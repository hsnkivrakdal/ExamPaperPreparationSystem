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
@Table(name = "examquestions")
public class Examquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamQuestionId", nullable = false)
    private Integer id;

    @Column(name = "ExamQuestionDefinition", nullable = false, length = 550)
    private String examQuestionDefinition;

    @Column(name = "QuestionPoint")
    private Short questionPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QuestionTypeId")
    private Questiontype questionType;

    @OneToMany(mappedBy = "examQuestion")
    private Set<Exampaperquestion> exampaperquestions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "examQuestion")
    private Set<Questionoption> questionoptions = new LinkedHashSet<>();

    @Override
    public String toString() {
        return examQuestionDefinition;
    }
}