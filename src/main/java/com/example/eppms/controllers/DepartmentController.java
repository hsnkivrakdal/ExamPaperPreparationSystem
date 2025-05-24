package com.example.eppms.controllers;

import com.example.eppms.models.Department;
import com.example.eppms.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("departments", service.getAll());
        return "departments/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("department", new Department());
        return "departments/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Department department) {
        service.add(department);
        return "redirect:/departments/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Department existing = service.getById(id);
        model.addAttribute("department", existing);
        return "departments/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Department department) {
        department.setId(id);
        service.update(department);
        return "redirect:/departments/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Department existing = service.getById(id);
        model.addAttribute("department", existing);
        return "departments/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/departments/list";
    }
}