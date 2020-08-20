package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudent_id(Long studentId);

}
//
//    @Query (nativeQuery = true, value = "SELECT student_id, first_name, last_name FROM student_records where student_id = ?1" )
//   Student findStudentByStudentId(Long student_id);
//}
