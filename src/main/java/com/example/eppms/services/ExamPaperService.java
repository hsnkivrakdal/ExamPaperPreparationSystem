package com.example.eppms.services;

import com.example.eppms.models.*;
import com.example.eppms.repositories.ExamPaperQuestionRepository;
import com.example.eppms.repositories.ExamPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamPaperService extends BusinessServiceImplementation<Exampaper, Integer> {
    public ExamPaperService(ExamPaperRepository repository) {
        super(repository);
    }

    @Autowired
    private CoursesExamService coursesExamService;

    @Autowired
    private LecturerService lecturerService;

    public List<Coursesexam> getAllCoursesexam() {
        List<Coursesexam> coursesexams = coursesExamService.getAll();
        return coursesexams;
    }
    public List<Lecturer> getAllLecturer() {
        List<Lecturer> lecturers = lecturerService.getAll();
        return lecturers;
    }

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private ExamPaperQuestionRepository examPaperQuestionRepository;

    public void saveWithQuestions(Exampaper exampaper, List<Integer> questionsIds) {
        Exampaper savedExamPaper =  repository.save(exampaper);

        List<Examquestion> examQuestions = examQuestionService.repository.findAllById(questionsIds);

        for (Examquestion examquestion : examQuestions) {
            Exampaperquestion epq = new Exampaperquestion();
            epq.setExamQuestion(examquestion);
            epq.setExamPaper(savedExamPaper);
            examPaperQuestionRepository.save(epq);
        }
    }
}
