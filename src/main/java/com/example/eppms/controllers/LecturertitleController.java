package com.example.eppms.controllers;

import com.example.eppms.models.Lecturertitle;
import com.example.eppms.services.LecturertitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturertitles")
public class LecturertitleController {

    @Autowired
    private LecturertitleService lecturertitleService;

    @GetMapping("/list")
    public String getLecturertitles(Model model) {
        model.addAttribute("lecturertitles", lecturertitleService.getAll());
        return "lecturertitles/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("lecturertitle", new Lecturertitle());
        return "lecturertitles/create";
    }

    @PostMapping("/create")
    public String createLecturertitle(@ModelAttribute Lecturertitle lecturertitle) {
        lecturertitleService.add(lecturertitle);
        return "redirect:/lecturertitles/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Lecturertitle lecturertitle = lecturertitleService.getById(id);
        model.addAttribute("lecturertitle", lecturertitle);
        return "lecturertitles/edit";
    }

    @PostMapping("/update")
    public String updateLecturertitle(@ModelAttribute Lecturertitle lecturertitle) {
        lecturertitleService.update(lecturertitle);
        return "redirect:/lecturertitles/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteLecturertitle(@PathVariable Integer id) {
        lecturertitleService.deleteById(id);
        return "redirect:/lecturertitles/list";
    }
}
