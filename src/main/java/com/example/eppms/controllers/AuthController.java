package com.example.eppms.controllers;

import com.example.eppms.models.User;
import com.example.eppms.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        HttpSession session,
                        Model model) {
        
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password!");
        }
        
        if (logout != null) {
            model.addAttribute("successMessage", "You have been logged out successfully!");
        }
        
        return "auth/login";
    }
    
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home/index";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpSession session, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmailWithRoles(email).orElseThrow();
        session.setAttribute("ROLE", user.getUserinroles().stream()
                .findFirst()
                .map(r -> r.getRole().getRoleTitle())
                .orElse("GUEST"));
        session.setAttribute("userId", user.getId());

        return "redirect:/home/index";
    }

} 