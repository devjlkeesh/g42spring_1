package dev.jlkeesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/home/{id}/admin/{aid}")
    public ModelAndView home(@PathVariable(name = "id") String id,
                             @RequestParam("username") String username,
                             @PathVariable(name = "aid") String aid) {
        Map<String, String> model = Map.of("message", "Hello " + username, "time", LocalTime.now().toString());
        ModelAndView mav = new ModelAndView("home", model);
        System.out.println(id);
        System.out.println(aid);
        return mav;
    }

    @RequestMapping("/home2")
    public String home2() {
        return "home";
    }

    @RequestMapping(value = "/home3", method = {
            RequestMethod.GET, RequestMethod.HEAD
    })
    public String home3(Model model) {
        model.addAttribute("message", "Hello World");
        model.addAttribute("time", LocalTime.now());
        return "home";
    }

}
