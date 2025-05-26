package com.example.eppms.controllers;

import com.example.eppms.models.Program;
import com.example.eppms.services.ProgramService;
import com.example.eppms.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    private ProgramService service;
    
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("programs", service.getAll());
        return "programs/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("program", new Program());
        model.addAttribute("departments", departmentService.getAll());
        return "programs/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Program program) {
        service.add(program);
        return "redirect:/programs/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Program existing = service.getById(id);
        model.addAttribute("program", existing);
        model.addAttribute("departments", departmentService.getAll());
        return "programs/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Program program) {
        service.update(program);
        return "redirect:/programs/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Program existing = service.getById(id);
        model.addAttribute("program", existing);
        return "programs/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/programs/list";
    }
}