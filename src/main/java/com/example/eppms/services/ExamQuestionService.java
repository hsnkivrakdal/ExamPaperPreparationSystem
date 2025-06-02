package com.example.eppms.services;

import com.example.eppms.models.Examquestion;
import com.example.eppms.models.Questiontype;
import com.example.eppms.repositories.ExamQuestionRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamQuestionService extends BusinessServiceImplementation<Examquestion, Integer> {
    public ExamQuestionService(ExamQuestionRepository repository) {
        super(repository);
    }

    @Autowired
    public QuestionTypeService questionTypeService;

    public List<Questiontype> getQuestionTypes() {
        List<Questiontype> questiontypes = questionTypeService.getAll();
        return questiontypes;
    }

    public List<Examquestion> getQuestionsByIds(List<Integer> ids) {
        List<Examquestion> examquestions = repository.findAllById(ids);
        return examquestions;
    }
}
