package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.models.Yearbook;
import com.codeup.eyearbook.repositories.UserRepository;
import com.codeup.eyearbook.repositories.YearbookRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YearbookController {
    private UserRepository users;
    private YearbookRepository yearbook;

    public YearbookController(YearbookRepository yearbook, UserRepository users) {
        this.users = users;
        this.yearbook = yearbook;
    }

    @RequestMapping("/yearbook")
    public String home(Model model){
        return "users/yearbook";
    }
}
