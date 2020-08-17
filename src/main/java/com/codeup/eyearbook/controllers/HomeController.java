package com.codeup.eyearbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String test(){
        return "index";
    }

    @GetMapping("/landingpage")
    public String landingPage(){
        return "landing";
    }


}