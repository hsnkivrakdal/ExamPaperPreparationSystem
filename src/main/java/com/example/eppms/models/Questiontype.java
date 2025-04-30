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
@Table(name = "questiontypes")
public class Questiontype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionTypeId", nullable = false)
    private Integer id;

    @Column(name = "QuestionTypeDefinition", nullable = false, length = 250)
    private String questionTypeDefinition;

    @OneToMany(mappedBy = "questionType")
    private Set<Examquestion> examquestions = new LinkedHashSet<>();

}