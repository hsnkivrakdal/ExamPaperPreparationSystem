package com.example.eppms.controllers;

import com.example.eppms.models.Cours;
import com.example.eppms.models.Department;
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

    // ✅ Listeleme Sayfası
    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("courses", service.getAll());
        return "courses/index"; // templates/courses/index.html
    }

    // ✅ CREATE - Sayfa Gösterimi
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("courses", new Cours());
        model.addAttribute("programs", service.getProgram());
        return "courses/create"; // templates/courses/create.html
    }

    // ✅ CREATE - Form Gönderimi

    @PostMapping("/create")
    public String create(@ModelAttribute Cours cours) {
        service.add(cours);
        return "redirect:/courses/list";
    }

    // ✅ EDIT - Sayfa Gösterimi
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Cours existing = service.getById(id);
        model.addAttribute("courses", existing);
        model.addAttribute("programs", service.getProgram()); // Program dropdown için!
        return "courses/edit"; // templates/courses/edit.html
    }

    // ✅ EDIT - Form Gönderimi
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute("courses") Cours course) {
        course.setId(id);
        service.update(course);
        return "redirect:/courses/list";
    }

    // ✅ DELETE - Onay Sayfası (İsteğe Bağlı)
    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Cours course = service.getById(id);
        model.addAttribute("courses", course);
        return "courses/delete"; // templates/courses/delete.html
    }

    // ✅ DELETE - Form Gönderimi
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/courses/list";
    }
}
