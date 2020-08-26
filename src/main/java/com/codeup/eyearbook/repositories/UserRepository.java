package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    User findByParent_id(long id);

//    @Query("UPDATE users SET Student s WHERE s.first_name LIKE %?1%"
//            + " OR s.last_name LIKE %?1%")
//
//    User editProfile();

}