package com.codeup.eyearbook.models;

import com.codeup.eyearbook.repositories.StudentRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchStudent {
    @Autowired
    private StudentRepository repo;
    private UserRepository userRepo;

    public List<Student> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

//    public List<User>  listAllStudents(String keyword){
//        if (keyword != null) {
//            return userRepo.searchStudent(keyword);
//        }
//        return  userRepo.findAll();
//    }


}
