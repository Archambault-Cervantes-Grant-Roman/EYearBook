package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Service;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    Student findByStudent_id(Long studentId);

    List<Student> findAllByOrderByIdDesc();


        @Query("SELECT s FROM Student s WHERE s.first_name LIKE %?1%"
                + " OR s.last_name LIKE %?1%")

         List<Student> search(String keyword);


    @Query (value = "select * FROM student_records where student_id = ?1", nativeQuery = true)
    Student getByStudent_id(long student_id);

//@Query ("select student_id, first_name, last_name FROM student_records where student_id = ?1")
//    Student getByStudent_id(long id);

    }









