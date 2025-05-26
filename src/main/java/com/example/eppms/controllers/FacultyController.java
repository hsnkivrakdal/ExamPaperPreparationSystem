package com.example.eppms.controllers;

import com.example.eppms.models.Faculty;
import com.example.eppms.services.FacultyService;
import com.example.eppms.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService service;
    
    @Autowired
    private UniversityService universityService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("faculties", service.getAll());
        return "faculties/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("faculty", new Faculty());
        model.addAttribute("universities", universityService.getAll());
        return "faculties/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Faculty faculty) {
        service.add(faculty);
        return "redirect:/faculties/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Faculty existing = service.getById(id);
        model.addAttribute("faculty", existing);
        model.addAttribute("universities", universityService.getAll());
        return "faculties/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Faculty faculty) {
        service.update(faculty);
        return "redirect:/faculties/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Faculty existing = service.getById(id);
        model.addAttribute("faculty", existing);
        return "faculties/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/faculties/list";
    }
}