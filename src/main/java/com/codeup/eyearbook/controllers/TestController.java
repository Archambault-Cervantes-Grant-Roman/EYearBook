package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private UserRepository userDao;

    @GetMapping("/test/repo")
    @ResponseBody
    public String testRepo() {

        return userDao.findByParent_id(12).get(2).getStudent().getLast_name();
    }


}
