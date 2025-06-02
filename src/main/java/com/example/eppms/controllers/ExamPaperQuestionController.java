package com.example.eppms.controllers;

import com.example.eppms.models.Exampaper;
import com.example.eppms.models.Exampaperquestion;
import com.example.eppms.services.ExamPaperQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exam-paper-question")
public class ExamPaperQuestionController {
    @Autowired
    private ExamPaperQuestionService examPaperQuestionService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("examPaperQuestions", examPaperQuestionService.getAll());
        return "exampaperquestions/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("examPaperQuestions", new Exampaperquestion());
        model.addAttribute("examPapers", examPaperQuestionService.getAllExamPapers());
        model.addAttribute("examQuestions", examPaperQuestionService.getAllQuestions());
        return "exampaperquestions/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Exampaperquestion exampaperquestion) {
        examPaperQuestionService.add(exampaperquestion);
        return "redirect:/exam-paper-question/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Exampaperquestion existing = examPaperQuestionService.getById(id);
        model.addAttribute("examPaperQuestions", existing);
        model.addAttribute("examPapers", examPaperQuestionService.getAllExamPapers());
        model.addAttribute("examQuestions", examPaperQuestionService.getAllQuestions());
        return "exampaperquestions/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Exampaperquestion exampaperquestion) {
        exampaperquestion.setId(id);
        examPaperQuestionService.update(exampaperquestion);
        return "redirect:/exam-paper-question/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Exampaperquestion existing = examPaperQuestionService.getById(id);
        model.addAttribute("examPaperQuestions", existing);
        return "exampaperquestions/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        examPaperQuestionService.deleteById(id);
        return "redirect:/exam-paper-question/list";
    }
}
