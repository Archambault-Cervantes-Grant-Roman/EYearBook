package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.SearchStudent;
import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.StudentRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentSearchController {
    @Autowired
    private final SearchStudent service;
    private final StudentRepository studentDao;
    private final UserRepository userDao;

    public StudentSearchController(SearchStudent service, StudentRepository studentDao, UserRepository userDao) {
        this.service = service;
        this.studentDao = studentDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/search", method= RequestMethod.GET)
    public String searchStudents(Model model, @Param("keyword") String keyword) {

//        model.addAttribute("students", studentDao.findAllByOrderByIdDesc());


        List<Student> listStudents = service.listAll(keyword);

        List<User> listUsers = new ArrayList<>();
        for(Student student1: listStudents ){
            listUsers.add(userDao.getByStudent_id(student1.getStudent_id()));
        }

        model.addAttribute("listStudents", listStudents);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listUsers", listUsers);
        System.out.println(listStudents);
        System.out.println(listStudents);


        return "users/search";
    }


}
