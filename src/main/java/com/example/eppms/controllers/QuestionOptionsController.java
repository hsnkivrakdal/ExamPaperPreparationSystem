package com.example.eppms.controllers;

import com.example.eppms.models.Examquestion;
import com.example.eppms.models.Questionoption;
import com.example.eppms.services.QuestionOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question-option")
public class QuestionOptionsController {

    @Autowired
    private QuestionOptionsService questionOptionsService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("questionOptions", questionOptionsService.getAll());
        return "questionoptions/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("questionOptions", new Questionoption());
        model.addAttribute("questionType", questionOptionsService.getAllExamQuestions());
        return "questionoptions/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Questionoption questionoption) {
        questionOptionsService.add(questionoption);
        return "redirect:/question-option/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Questionoption existing = questionOptionsService.getById(id);
        model.addAttribute("questionOptions", existing);
        model.addAttribute("questionType", questionOptionsService.getAllExamQuestions());
        return "questionoptions/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Questionoption questionoption) {
        questionoption.setId(id);
        questionOptionsService.update(questionoption);
        return "redirect:/question-option/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Questionoption existing = questionOptionsService.getById(id);
        model.addAttribute("questionOptions", existing);
        return "questionoptions/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        questionOptionsService.deleteById(id);
        return "redirect:/question-option/list";
    }
}
