package com.example.eppms.controllers;

import com.example.eppms.models.Coursesexam;
import com.example.eppms.models.Faculty;
import com.example.eppms.services.CourseService;
import com.example.eppms.services.CoursesExamService;
import com.example.eppms.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses-exams")
public class CoursesExamController {

    @Autowired
    private CoursesExamService service;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ExamTypeService examTypeService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("coursesexams", service.getAll());
        return "coursesexams/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("coursesexam", new Coursesexam());
        model.addAttribute("coursList", courseService.getAll());
        model.addAttribute("examtypeList", examTypeService.getAll());
        return "coursesexams/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Coursesexam coursesexam) {
        service.add(coursesexam);
        return "redirect:/courses-exams/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        model.addAttribute("coursesexam", service.getById(id));
        model.addAttribute("coursList", courseService.getAll());
        model.addAttribute("examtypeList", examTypeService.getAll());
        return "coursesexams/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Coursesexam coursesexam) {
        coursesexam.setId(id);
        service.update(coursesexam);
        return "redirect:/courses-exams/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        model.addAttribute("coursesexam", service.getById(id));
        return "coursesexams/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/courses-exams/list";
    }
}
