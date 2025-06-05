package com.example.eppms.strategy;

import com.example.eppms.models.Examquestion;
import java.util.List;

public interface ExamQuestionStrategy {
    List<Examquestion> filter(List<Examquestion> questions);
}
