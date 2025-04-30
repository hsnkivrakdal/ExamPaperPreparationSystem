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
@Table(name = "exampaperquestions")
public class Exampaperquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamPaperQuestionsId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ExamPaperId")
    private Exampaper examPaper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ExamQuestionId")
    private Examquestion examQuestion;

}