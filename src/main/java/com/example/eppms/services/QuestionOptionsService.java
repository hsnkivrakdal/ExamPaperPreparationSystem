package com.example.eppms.services;

import com.example.eppms.models.Examquestion;
import com.example.eppms.models.Questionoption;
import com.example.eppms.repositories.QuetionOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionOptionsService extends BusinessServiceImplementation<Questionoption, Integer> {
    public QuestionOptionsService(QuetionOptionsRepository repository) {
        super(repository);
    }

    @Autowired
    private ExamQuestionService examQuestionService;

    public List<Examquestion> getAllExamQuestions() {
        List<Examquestion> examquestions = examQuestionService.getAll();
        return examquestions;
    }
}
