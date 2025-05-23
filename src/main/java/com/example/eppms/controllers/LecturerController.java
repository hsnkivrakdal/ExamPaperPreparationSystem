package com.example.eppms.controllers;

import com.example.eppms.models.Lecturer;
import com.example.eppms.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;


    @GetMapping("/list")
    public String getLecturers(Model model) {
        model.addAttribute("lecturers", lecturerService.getAll());
        return "lecturers/index";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        return "lecturers/create";
    }


    @PostMapping("/create")
    public String createLecturer(@ModelAttribute Lecturer lecturer) {
        lecturerService.add(lecturer);
        return "redirect:/lecturers/list";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Lecturer lecturer = lecturerService.getById(id);
        model.addAttribute("lecturer", lecturer);
        return "lecturers/edit";
    }


    @PostMapping("/update")
    public String updateLecturer(@ModelAttribute Lecturer lecturer) {
        lecturerService.update(lecturer);
        return "redirect:/lecturers/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteLecturer(@PathVariable Integer id) {
        lecturerService.deleteById(id);
        return "redirect:/lecturers/list";
    }
}
