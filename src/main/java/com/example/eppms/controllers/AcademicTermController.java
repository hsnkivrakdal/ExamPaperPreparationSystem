package com.example.eppms.controllers;

import com.example.eppms.models.Academicterm;
import com.example.eppms.services.AcademicTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/academicterms")
public class AcademicTermController {

    @Autowired
    private AcademicTermService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("academicterms", service.getAll());
        return "academicterms/index"; // templates/academicterms/index.html
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("academicterm", new Academicterm());
        return "academicterms/create"; // templates/academicterms/create.html
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Academicterm academicterm) {
        service.add(academicterm);
        return "redirect:/academicterms/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable int id, Model model) {
        Academicterm existing = service.getById(id);
        model.addAttribute("academicterm", existing);
        return "academicterms/edit"; // templates/academicterms/edit.html
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute Academicterm academicterm) {
        academicterm.setId(id);
        service.update(academicterm);
        return "redirect:/academicterms/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable int id, Model model) {
        Academicterm existing = service.getById(id);
        model.addAttribute("academicterm", existing);
        return "academicterms/delete"; // templates/academicterms/delete.html
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/academicterms/list";
    }
}
