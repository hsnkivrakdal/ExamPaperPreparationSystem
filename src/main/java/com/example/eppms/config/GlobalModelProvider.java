package com.example.eppms.config;

import com.example.eppms.menucomposite.MenuComponent;
import com.example.eppms.menucomposite.MenuFactory;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalModelProvider {

    @ModelAttribute("menu")
    public List<MenuComponent> provideMenu(HttpSession session) {
        return MenuFactory.createMenuForSessionRole(    session);
    }
}
