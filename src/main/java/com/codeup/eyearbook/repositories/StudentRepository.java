package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<User, Long>  {
}
