package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private UserRepository users;

    public HomeController(UserRepository users){
        this.users = users;
    }


    @RequestMapping("/")
    public String home() {
        return "home";
    }

//    @RequestMapping("/")
//    public String home(Model model) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = users.getOne(loggedInUser.getId());
//        model.addAttribute(user);
//        return "home";
//    }


}