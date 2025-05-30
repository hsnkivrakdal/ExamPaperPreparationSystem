package com.example.eppms.controllers;

import com.example.eppms.models.Examtype;
import com.example.eppms.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exam-types")
public class ExamTypeController {

    @Autowired
    private ExamTypeService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("examtypes", service.getAll());
        return "examtypes/index";
    }

    @GetMapping("/create")
    public String getCreateExamTypePage(Model model) {
        model.addAttribute("examtype", new Examtype());
        return "examtypes/create";
    }

    @PostMapping("/create")
    public String createExamType(@ModelAttribute Examtype examtype) {
        service.add(examtype);
        return "redirect:/exam-types/list";
    }

    @GetMapping("/edit/{id}")
    public String editExamTypePage(@PathVariable Integer id, Model model) {
        Examtype existing = service.getById(id);
        model.addAttribute("examtype", existing);
        return "examtypes/edit";
    }

    @PostMapping("/edit/{id}")
    public String editExamType(@PathVariable Integer id, @ModelAttribute Examtype examtype) {
        examtype.setId(id);
        service.update(examtype);
        return "redirect:/exam-types/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteExamTypePage(@PathVariable Integer id, Model model) {
        Examtype existing = service.getById(id);
        model.addAttribute("examtype", existing);
        return "examtypes/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteExamType(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/exam-types/list";
    }
}
