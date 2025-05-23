package com.example.eppms.controllers;

import com.example.eppms.models.Coursesexam;
import com.example.eppms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("courses", service.getAll());
        return "courses/index"; // templates/courses/index.html
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("course", new Coursesexam());
        return "courses/create"; // templates/courses/create.html
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Coursesexam course) {
        service.add(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Coursesexam existing = service.getById(id);
        model.addAttribute("course", existing);
        return "courses/edit"; // templates/courses/edit.html
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Coursesexam course) {
        course.setId(id);
        service.update(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Coursesexam existing = service.getById(id);
        model.addAttribute("course", existing);
        return "courses/delete"; // templates/courses/delete.html
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/courses/list";
    }
}
