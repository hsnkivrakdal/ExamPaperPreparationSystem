package com.example.eppms.controllers;

import com.example.eppms.models.Examquestion;
import com.example.eppms.services.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exam-question")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("examQuestion", examQuestionService.getAll());
        return "examquestions/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("examQuestion", new Examquestion());
        model.addAttribute("questionType", examQuestionService.getQuestionTypes());
        return "examquestions/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Examquestion examquestion) {
        examQuestionService.add(examquestion);
        return "redirect:/exam-question/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Examquestion existing = examQuestionService.getById(id);
        model.addAttribute("examQuestion", existing);
        model.addAttribute("questionType", examQuestionService.getQuestionTypes());
        return "examquestions/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Examquestion examquestion) {
        examquestion.setId(id);
        examQuestionService.update(examquestion);
        return "redirect:/exam-question/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Examquestion existing = examQuestionService.getById(id);
        model.addAttribute("examQuestion", existing);
        return "examquestions/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        examQuestionService.deleteById(id);
        return "redirect:/exam-question/list";
    }
}
