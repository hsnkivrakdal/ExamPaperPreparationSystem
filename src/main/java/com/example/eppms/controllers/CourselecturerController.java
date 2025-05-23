package com.example.eppms.controllers;

import com.example.eppms.models.Courselecturer;
import com.example.eppms.services.CourselecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courselecturers")
public class CourselecturerController {

    @Autowired
    private CourselecturerService courselecturerService;


    @GetMapping("/list")
    public String getCourseLecturers(Model model) {
        model.addAttribute("courselecturers", courselecturerService.getAll());
        return "courselecturers/index";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("courselecturer", new Courselecturer());
        return "courselecturers/create";
    }


    @PostMapping("/create")
    public String createCourseLecturer(@ModelAttribute Courselecturer courselecturer) {
        courselecturerService.add(courselecturer);
        return "redirect:/courselecturers/list";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Courselecturer courselecturer = courselecturerService.getById(id);
        model.addAttribute("courselecturer", courselecturer);
        return "courselecturers/edit";
    }


    @PostMapping("/update")
    public String updateCourseLecturer(@ModelAttribute Courselecturer courselecturer) {
        courselecturerService.update(courselecturer);
        return "redirect:/courselecturers/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteCourseLecturer(@PathVariable Integer id) {
        courselecturerService.deleteById(id);
        return "redirect:/courselecturers/list";
    }
}
