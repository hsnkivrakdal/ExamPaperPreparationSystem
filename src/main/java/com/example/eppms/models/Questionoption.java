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
@Table(name = "questionoptions")
public class Questionoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionOptionId", nullable = false)
    private Integer id;

    @Column(name = "QuestionOptionDefinition", nullable = false, length = 250)
    private String questionOptionDefinition;

    @Column(name = "IsCorrectAnswer")
    private Boolean isCorrectAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ExamQuestionId")
    private Examquestion examQuestion;

}