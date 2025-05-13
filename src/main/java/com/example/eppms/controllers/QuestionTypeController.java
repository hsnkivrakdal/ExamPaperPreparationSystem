package com.example.eppms.controllers;

import com.example.eppms.services.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question-types")
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;

    @GetMapping("/list")
    public String getQuestionTypes(Model model) {
        model.addAttribute("questionTypes",questionTypeService.getAll());
        return "questiontypes/index";
    }
}
