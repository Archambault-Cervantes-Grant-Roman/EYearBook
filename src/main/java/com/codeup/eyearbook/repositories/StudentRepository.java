package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long>  {



    List<Student> findAllByOrderByIdDesc();



//    @Query (value = "select student_id, first_name, last_name FROM student_records where student_id = ?1", nativeQuery = true)
//   Student findStudentByStudentId(Student student_id);

}
