package com.example.eppms.controllers;

import com.example.eppms.models.Lecturer;
import com.example.eppms.services.DepartmentService;
import com.example.eppms.services.LecturerService;
import com.example.eppms.services.LecturertitleService;
import com.example.eppms.services.UserService;
import com.example.eppms.strategy.LecturerFilterContext;
import com.example.eppms.strategy.WithPhoneLecturerStrategy;
import com.example.eppms.strategy.WithoutPhoneLecturerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LecturertitleService lecturertitleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listLecturers(@RequestParam(required = false) String filterType, Model model) {
        List<Lecturer> lecturers = lecturerService.getAll();

        if (filterType != null && !filterType.isEmpty()) {
            LecturerFilterContext context = new LecturerFilterContext();
            switch (filterType.toLowerCase()) {
                case "withphone":
                    context.setStrategy(new WithPhoneLecturerStrategy());
                    break;
                case "withoutphone":
                    context.setStrategy(new WithoutPhoneLecturerStrategy());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid filter type: " + filterType);
            }
            lecturers = context.executeStrategy(lecturers);
        }

        model.addAttribute("lecturers", lecturers);
        return "lecturers/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("lecturertitles", lecturertitleService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("users", userService.getAll());
        return "lecturers/create";
    }

    @PostMapping("/create")
    public String createLecturer(@ModelAttribute Lecturer lecturer) {
        lecturerService.add(lecturer);
        return "redirect:/lecturers/list";
    }


    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Integer id, Model model) {
        Lecturer lecturer = lecturerService.getById(id);
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("lecturertitles", lecturertitleService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("users", userService.getAll());
        return "lecturers/edit";
    }

    @PostMapping("/edit/{id}")
    public String editLecturer(@PathVariable Integer id, @ModelAttribute Lecturer lecturer) {
        lecturer.setId(id);
        lecturerService.update(lecturer);
        return "redirect:/lecturers/list";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage(@PathVariable Integer id, Model model) {
        Lecturer lecturer = lecturerService.getById(id);
        model.addAttribute("lecturer", lecturer);
        return "lecturers/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteLecturer(@PathVariable Integer id) {
        lecturerService.deleteById(id);
        return "redirect:/lecturers/list";
    }
}
