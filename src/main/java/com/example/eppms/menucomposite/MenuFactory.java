package com.example.eppms.menucomposite;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {
    public static List<MenuComponent> createMenuForSessionRole(HttpSession session) {
        String role = (String) session.getAttribute("ROLE");
        if (role == null) {
            role = "LECTURER";
        }

        List<MenuComponent> menuItems = new ArrayList<>();

        if ("ADMIN".equalsIgnoreCase(role)) {
            menuItems.add(new MenuItem("University", "/universities/list", "fa-university"));
            menuItems.add(new MenuItem("Faculty", "/faculties/list", "fa-building"));
            menuItems.add(new MenuItem("Department", "/departments/list", "fa-sitemap"));
            menuItems.add(new MenuItem("Program", "/programs/list", "fa-paperclip"));
        }

        menuItems.add(new MenuItem("New Exam Paper", "/exam-wizard/start", "fa-file"));
        menuItems.add(new MenuItem("Exam Papers", "/exam-paper/list", "fa-copy"));
        menuItems.add(new MenuItem("Exam Questions", "/exam-question/list", "fa-question"));
        menuItems.add(new MenuItem("Exam Paper Questions", "/exam-paper-question/list", "fa-question-circle"));
        menuItems.add(new MenuItem("Question Options", "/question-option/list", "fa-th-list"));

        if ("ADMIN".equalsIgnoreCase(role)) {
            menuItems.add(new MenuItem("Exam Types", "/examtypes", "fa-clipboard-list"));
            menuItems.add(new MenuItem("Question Types", "/questiontypes", "fa-question-circle"));
            menuItems.add(new MenuItem("Lecturers", "/lecturers", "fa-chalkboard-teacher"));
        }

        return menuItems;
    }
}
