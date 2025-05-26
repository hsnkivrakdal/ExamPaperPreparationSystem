package com.example.eppms.controllers;

import com.example.eppms.models.Questiontype;
import com.example.eppms.services.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questiontypes")
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;

    @GetMapping("/list")
    public String getQuestionTypes(Model model) {
        model.addAttribute("questionTypes",questionTypeService.getAll());
        return "questiontypes/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("questiontype", new Questiontype());
        return "questiontypes/create";
    }


    @PostMapping("/create")
    public String createQuestionType(@ModelAttribute Questiontype questiontype) {
        questionTypeService.add(questiontype);
        return "redirect:/questiontypes/list";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Questiontype questiontype = questionTypeService.getById(id);
        model.addAttribute("questiontype", questiontype);
        return "questiontypes/edit";
    }


    @PostMapping("/update")
    public String updateQuestionType(@ModelAttribute Questiontype questiontype) {
        questionTypeService.update(questiontype);
        return "redirect:/questiontypes/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteQuestionType(@PathVariable Integer id) {
        questionTypeService.deleteById(id);
        return "redirect:/questiontypes/list";
    }
}
