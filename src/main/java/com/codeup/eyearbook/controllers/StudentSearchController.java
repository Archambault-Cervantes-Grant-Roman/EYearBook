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

//    private void search(String name){
//        String [] = name.split(" ");
//        int count = 0;
//        for(int i=0; i < name.length(); )

//    }

    @RequestMapping(path = "/search", method= RequestMethod.GET)
    public String searchStudents(Model model, @Param("keyword") String keyword) {

        List<User> users = userDao.findAll();

        List<User> students = new ArrayList<>();

        List<User> filteredResults = new ArrayList<>();

        //Controller filter method
        for (User user : users) {
            if (!user.isParent()) {
                students.add(user);
            }
        }
//                filteredResults = students;
            for (User user: students) {

//                if      (keyword != null) {
                    System.out.println(keyword);
                    if (user.getUsername().equals(keyword) || (user.getStudent().getFirst_name().equals(keyword))) {
                        filteredResults.add(user);
                    }
//                    else {
//                        filteredResults = students;
                    }
//                }


//        for (student : students) {
//            if (student.getUsername().equals(query) || student.getStudent().getFirst_name().equals(query) || etc...) {
//                filteredResults.add(student);
//            }
//        }

//        for (User user : students) {
//            if (user.getUsername().equals(query) || user.getStudent().getFirst_name().equals(query) ) {
//                filteredResults.add(user);
//            }
//        }
                if (keyword == null || keyword.isEmpty()) {
                    filteredResults = students;
                }
                    model.addAttribute("filteredResults", filteredResults);
//        model.addAttribute("students", studentDao.findAllByOrderByIdDesc());

//       Student filter method based on student_id
//        List<User> listUser = service.listAllStudents(keyword);

//        List<User> listUsers = new ArrayList<>();
//        for(Student student1: listStudents ){
//            listUsers.add(userDao.getByStudent_id(student1.getStudent_id()));
//        }


                    model.addAttribute("keyword", keyword);
//        model.addAttribute("listUsers", listUsers);
//        System.out.println(listStudents);
//        System.out.println(listStudents);
                    System.out.println(users.toString());
                    System.out.println(students.toString());
                    return "users/search";
                }


            }
