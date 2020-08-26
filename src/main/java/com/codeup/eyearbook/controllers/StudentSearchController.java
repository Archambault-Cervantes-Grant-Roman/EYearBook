package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.SearchStudent;
import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.StudentRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentSearchController {
    @Autowired
    private SearchStudent service;
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private StudentRepository studentsDao;


    public StudentSearchController(UserRepository userDao, PasswordEncoder passwordEncoder, StudentRepository studentsDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.studentsDao = studentsDao;
    }

    @RequestMapping(path = "/search", method= RequestMethod.GET)
    public String searchStudents(Model model, @Param("keyword") String keyword, Student student) {

//        model.addAttribute("students", studentDao.findAllByOrderByIdDesc());


        List<Student> listStudents = service.listAll(keyword);
        // iterate thru list of students.
        List<User> listUsers = new ArrayList<>();
        for(Student student1: listStudents ){
            listUsers.add(userDao.getByStudent_id(student1.getStudent_id()));
        }

        System.out.println(listStudents);
        System.out.println(listUsers);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("listStudents", listStudents);
        model.addAttribute("keyword", keyword);

//        long studentId = student.getStudent_id();
//        User user = userDao.getOne(studentId);
//        model.addAttribute("user", user);




        return "users/search";
    }
//    @PostMapping("/search")
//    public String StudentToUser( Model model, Student student){
//        long studentId = student.getStudent_id();
//
//
//
//
//
//
//
//
//        return "users/search";
//    }


}
