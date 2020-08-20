package com.codeup.eyearbook.models;

import com.codeup.eyearbook.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchStudent {
    @Autowired
    private StudentRepository repo;

    public List<Student> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }



}
