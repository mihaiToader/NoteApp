package com.mkyong.controller;

import com.mkyong.Repository.RepositoryUser;
import com.mkyong.service.iService.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DefaultController {

    private final ServiceUser serviceUser;

    @Autowired
    public DefaultController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/")
    public String home1() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(ModelMap model, Principal principal) {
        if (principal != null){
            model.addAttribute("user", serviceUser.findByName(principal.getName()));
        }
        return "views/home";
    }

    @GetMapping("/admin")
    public String admin(ModelMap model, Principal principal) {
        model.addAttribute("user", serviceUser.findByName(principal.getName()));
        return "views/admin";
    }

    @GetMapping("/about")
    public String about(ModelMap model, Principal principal) {
        model.addAttribute("user", serviceUser.findByName(principal.getName()));
        model.addAttribute("welcome", "asd");
        return "views/about";
    }

    @GetMapping("/login")
    public String login() {
        return "views/login";
    }

    @GetMapping("/403")
    public String error403(ModelMap model, Principal principal) {
        if (principal != null){
            model.addAttribute("user", serviceUser.findByName(principal.getName()));
        }
        return "/error/403";
    }

}
