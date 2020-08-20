package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentSearchController {
    @Autowired
    private StudentSearchController service;
//
//    @RequestMapping("/search")
//    public String searchStudents(Model model, @Param("keyword") String keyword) {
//        List<Student> listStudents = service.listAll(keyword);
//        model.addAttribute("listProducts", listStudents);
//        model.addAttribute("keyword", keyword);
//
//        return "users/search";
//    }


}
