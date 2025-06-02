package com.example.eppms.controllers;

import com.example.eppms.models.Exampaper;
import com.example.eppms.models.Examquestion;
import com.example.eppms.services.CoursesExamService;
import com.example.eppms.services.ExamPaperService;
import com.example.eppms.services.ExamQuestionService;
import com.example.eppms.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam-wizard")
@SessionAttributes({"examPaper", "selectedQuestions"})
public class ExamPaperWizardController {
    @Autowired
    private ExamPaperService examPaperService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private CoursesExamService coursesExamService;

    @GetMapping("/start")
    public String startWizard(Model model) {
        model.addAttribute("examPaper", new Exampaper());
        model.addAttribute("lecturers", lecturerService.getAll());
        model.addAttribute("coursesExams", coursesExamService.getAll());
        return "wizard/step1";
    }

    @PostMapping("/step2")
    public String goToStep2(@ModelAttribute("examPaper") Exampaper examPaper,
                            Model model) {
        model.addAttribute("questions", examQuestionService.getAll());
        model.addAttribute("selectedQuestions", new ArrayList<Integer>());
        return "wizard/step2";
    }

    @PostMapping("/step3")
    public String goToStep3(@ModelAttribute("examPaper") Exampaper examPaper,
                            @RequestParam("selectedQuestions") List<Integer> questionIds,
                            Model model) {

        List<Examquestion> selected = examQuestionService.getQuestionsByIds(questionIds);

        int totalPoints = selected.stream()
                .mapToInt(Examquestion::getQuestionPoint)
                .sum();

        model.addAttribute("selectedQuestions", questionIds);
        model.addAttribute("examSummary", selected);
        model.addAttribute("totalPoints", totalPoints);
        return "wizard/step3";
    }

    @PostMapping("/complete")
    public String completeWizard(@ModelAttribute("examPaper") Exampaper examPaper,
                                 @ModelAttribute("selectedQuestions") List<Integer> questionIds) {
        examPaperService.saveWithQuestions(examPaper, questionIds);
        return "redirect:/exam-paper/list";
    }
}
