package com.example.eppms.services;

import com.example.eppms.models.Exampaper;
import com.example.eppms.models.Exampaperquestion;
import com.example.eppms.models.Examquestion;
import com.example.eppms.repositories.ExamPaperQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamPaperQuestionService extends BusinessServiceImplementation<Exampaperquestion, Integer>{
    public ExamPaperQuestionService(ExamPaperQuestionRepository repository) {
        super(repository);
    }
    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private ExamQuestionService examQuestionService;

    public List<Exampaper> getAllExamPapers(){
        List<Exampaper> exampapers = examPaperService.getAll();
        return exampapers;
    }
    public List<Examquestion> getAllQuestions(){
        List<Examquestion> examquestions = examQuestionService.getAll();
        return examquestions;
    }
}
