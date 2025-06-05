package com.example.eppms.strategy;

import com.example.eppms.models.Examquestion;
import java.util.List;
import java.util.stream.Collectors;

public class HardQuestionStrategy implements ExamQuestionStrategy {
    @Override
    public List<Examquestion> filter(List<Examquestion> questions) {
        return questions.stream()
                .filter(q -> q.getQuestionPoint() != null && q.getQuestionPoint() >= 25)
                .collect(Collectors.toList());
    }
}
