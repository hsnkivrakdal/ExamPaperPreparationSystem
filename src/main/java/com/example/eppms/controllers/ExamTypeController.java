package com.example.eppms.controllers;

import com.example.eppms.models.Examtype;
import com.example.eppms.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exam-types")
public class ExamTypeController {

    @Autowired
    private ExamTypeService service;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("examtypes", service.getAll());
        return "examtypes/index";
    }

    @GetMapping("/create")
    public String getCreateExamTypePage(Model model) {
        model.addAttribute("examtypes", new Examtype());
        return "examtypes/create";
    }

    @PostMapping("/create")
    public String createExamType(@RequestBody Examtype examtype) {
        service.add(examtype);
        return "redirect:/exam-types/list";
    }

    @GetMapping("/edit/{examtypeid}")
    public String editExamTypePage(@PathVariable Integer examtypeid ,Model model) {
        Examtype existing = service.getById(examtypeid);
        model.addAttribute("examtype",existing);
        return "examtypes/edit";
    }

    @PostMapping("/edit/{examtypeid}")
    public String editExamType(@PathVariable Integer examtypeid, Examtype examtype) {
        examtype.setId(examtypeid);
        service.update(examtype);
        return "redirect:/exam-types/list";
    }

    @GetMapping("/delete/{examtypeid}")
    public String deleteExamTypePage(@PathVariable Integer examtypeid, Model model) {
        Examtype existing = service.getById(examtypeid);
        model.addAttribute("examtype", existing);
        return "examtypes/delete";
    }

    @DeleteMapping("/delete/{examtypeid}")
    public String deleteExamType(@PathVariable Integer examtypeid, Examtype examtype) {
        examtype.setId(examtypeid);
        service.delete(examtype);
        return "redirect:/exam-types/list";
    }

}
