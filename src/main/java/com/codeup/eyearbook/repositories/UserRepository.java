package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    User findByParent_id(long id);

    @Query (value = "select * FROM users where student_id = ?1", nativeQuery = true)
    User getByStudent_id(long student_id);
//    @Query ("from User u where u.student.first_name ")
//    List<User> search()

//            @Query (value= "SELECT u from User u, Student s where u.student.student_id = s.student_id and s.first_name LIKE %?1 OR s.last_name LIKE %?1%")
//    @Query (value = "SELECT u FROM User u  Student s where u.student.student_id = s.student_id and s.first_name LIKE %?1 OR s.last_name LIKE %?1%" )
//    List<User> searchStudent(String keyword);
//
//    List<Student> search(String keyword);

//    @Query("UPDATE users SET Student s WHERE s.first_name LIKE %?1%"
//            + " OR s.last_name LIKE %?1%")
//
//    User editProfile();

}