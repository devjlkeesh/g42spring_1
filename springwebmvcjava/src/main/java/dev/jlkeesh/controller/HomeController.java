package dev.jlkeesh.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {
            "/home",
            "/main"
    })
    public String home(Model model, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        model.addAttribute("title", "Home Page 😂😂");
        model.addAttribute("requestedFrom", requestURI);
        return "home";
    }
}
