package com.example.eppms.controllers;

import com.example.eppms.models.University;
import com.example.eppms.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("universities", service.getAll());
        return "universities/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("university", new University());
        return "universities/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute University university) {
        service.add(university);
        return "redirect:/universities/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        University existing = service.getById(id);
        model.addAttribute("university", existing);
        return "universities/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute University university) {
        service.update(university);
        return "redirect:/universities/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        University existing = service.getById(id);
        model.addAttribute("university", existing);
        return "universities/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/universities/list";
    }
}