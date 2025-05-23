package com.example.eppms.controllers;

import com.example.eppms.models.Coursesexam;
import com.example.eppms.services.CoursesExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses-exams")
public class CoursesExamController {

    @Autowired
    private CoursesExamService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("coursesexams", service.getAll());
        return "coursesexams/index"; // templates/coursesexams/index.html
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("coursesexam", new Coursesexam());
        return "coursesexams/create"; // templates/coursesexams/create.html
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Coursesexam coursesexam) {
        service.add(coursesexam);
        return "redirect:/courses-exams/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Coursesexam existing = service.getById(id);
        model.addAttribute("coursesexam", existing);
        return "coursesexams/edit"; // templates/coursesexams/edit.html
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Coursesexam coursesexam) {
        coursesexam.setId(id);
        service.update(coursesexam);
        return "redirect:/courses-exams/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Coursesexam existing = service.getById(id);
        model.addAttribute("coursesexam", existing);
        return "coursesexams/delete"; // templates/coursesexams/delete.html
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/courses-exams/list";
    }
}
