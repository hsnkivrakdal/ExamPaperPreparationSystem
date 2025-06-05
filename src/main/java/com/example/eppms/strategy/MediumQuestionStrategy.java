package com.example.eppms.strategy;

import com.example.eppms.models.Examquestion;
import java.util.List;
import java.util.stream.Collectors;

public class MediumQuestionStrategy implements ExamQuestionStrategy {
    @Override
    public List<Examquestion> filter(List<Examquestion> questions) {
        return questions.stream()
                .filter(q -> {
                    Short point = q.getQuestionPoint();
                    return point != null && point >= 10 && point < 25;
                })
                .collect(Collectors.toList());
    }
}
