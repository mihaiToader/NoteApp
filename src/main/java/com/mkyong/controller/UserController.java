package com.mkyong.controller;

import com.mkyong.Repository.RepositoryUser;
import com.mkyong.domain.User;
import com.mkyong.domain.fto.UserFto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class UserController {

    private final RepositoryUser userDao;

    @Autowired
    public UserController(RepositoryUser userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/registerSubmit")
    public String registerSubmit(@Valid UserFto cFto, Model model, Principal principal) {
        if (!cFto.getPassword().equals(cFto.getRepeatPassword())){
            model.addAttribute("tUser", new UserFto());
            model.addAttribute("error", true);
            return "views/register";
        }
        if (userDao.findByName(cFto.getName()) != null){
            model.addAttribute("tUser", new UserFto());
            model.addAttribute("error2", true);
            return "views/register";
        }

        User user = null;
        try {
            user = new User(cFto.getPassword(), cFto.getName());
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "views/login";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("tUser", new UserFto());
        return "views/register";
    }
}
