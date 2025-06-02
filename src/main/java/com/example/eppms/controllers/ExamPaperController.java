package com.example.eppms.controllers;

import com.example.eppms.models.Exampaper;
import com.example.eppms.services.ExampaperPdfExportService;
import com.example.eppms.services.ExamPaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse; // <-- Spring Boot 3 için GÜNCELLENDİ

@Controller
@RequestMapping("/exam-paper")
public class ExamPaperController {

    private final ExamPaperService examPaperService;

    public ExamPaperController(ExamPaperService examPaperService) {
        this.examPaperService = examPaperService;
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("examPapers", examPaperService.getAll());
        return "exampapers/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("examPapers", new Exampaper());
        model.addAttribute("lecturers", examPaperService.getAllLecturer());
        model.addAttribute("coursesExams", examPaperService.getAllCoursesexam());
        return "exampapers/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Exampaper exampaper) {
        examPaperService.add(exampaper);
        return "redirect:/exam-paper/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Exampaper existing = examPaperService.getById(id);
        model.addAttribute("examPapers", existing);
        model.addAttribute("lecturers", examPaperService.getAllLecturer());
        model.addAttribute("coursesExams", examPaperService.getAllCoursesexam());
        return "exampapers/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Exampaper exampaper) {
        exampaper.setId(id);
        examPaperService.update(exampaper);
        return "redirect:/exam-paper/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model) {
        Exampaper existing = examPaperService.getById(id);
        model.addAttribute("examPapers", existing);
        return "exampapers/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        examPaperService.deleteById(id);
        return "redirect:/exam-paper/list";
    }

    // ✅ PDF EXPORT - Sıra düzeltildi: (response, exampaper)
    @GetMapping("/export-pdf/{id}")
    public void exportPdf(@PathVariable Integer id, HttpServletResponse response) {
        try {
            Exampaper exampaper = examPaperService.getById(id);
            ExampaperPdfExportService.getInstance().export(response, exampaper); // <-- düzeltildi
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
