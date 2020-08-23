package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface
UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

//    @Query("UPDATE users SET Student s WHERE s.first_name LIKE %?1%"
//            + " OR s.last_name LIKE %?1%")
//
//    User editProfile();

}