package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.SearchStudent;
import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentSearchController {
    @Autowired
    private final SearchStudent service;
    private final StudentRepository studentDao;

    public StudentSearchController(SearchStudent service, StudentRepository studentDao) {
        this.service = service;
        this.studentDao = studentDao;
    }

    @RequestMapping(path = "/search", method= RequestMethod.GET)
    public String searchStudents(Model model, @Param("keyword") String keyword) {

//        model.addAttribute("students", studentDao.findAllByOrderByIdDesc());


        List<Student> listStudents = service.listAll(keyword);

        model.addAttribute("listStudents", listStudents);
        model.addAttribute("keyword", keyword);


        return "users/search";
    }

    @RequestMapping(path = "/yearbook", method= RequestMethod.GET)
    public String searchStudentsYearbook(Model model, @Param("keyword") String keyword) {

//        model.addAttribute("students", studentDao.findAllByOrderByIdDesc());


        List<Student> listStudents = service.listAll(keyword);

        model.addAttribute("listStudents", listStudents);
        model.addAttribute("keyword", keyword);


        return "users/yearbook";
    }


}
