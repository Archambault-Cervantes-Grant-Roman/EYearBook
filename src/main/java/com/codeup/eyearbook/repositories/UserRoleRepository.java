package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Role;
import com.codeup.eyearbook.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRoleRepository extends JpaRepository<Role, Integer> {


}
