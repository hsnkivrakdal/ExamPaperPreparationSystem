package com.example.eppms.strategy;

import com.example.eppms.models.Examquestion;
import java.util.List;

public class ExamQuestionFilterContext {
    private ExamQuestionStrategy strategy;

    public void setStrategy(ExamQuestionStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Examquestion> executeStrategy(List<Examquestion> questions) {
        if (strategy == null) return questions;
        return strategy.filter(questions);
    }
}
