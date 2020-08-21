package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.StudentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentDao;


    public StudentController(StudentRepository studentDao) {
        this.studentDao = studentDao;
    }
//    @GetMapping("/search")
//    @ResponseBody
//    public List<Student> getStudent() {
//        return studentDao.findAll();
//    }
//    @GetMapping("/search/view")
//    public String getAdsIndex(Model model) {
//        model.addAttribute("students", studentDao.findAllByOrderByIdDesc());
//        return "users/search";
//    }


}
