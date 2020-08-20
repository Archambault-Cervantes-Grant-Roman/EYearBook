package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}